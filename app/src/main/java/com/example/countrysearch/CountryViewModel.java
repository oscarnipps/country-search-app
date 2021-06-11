package com.example.countrysearch;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends AndroidViewModel {

    private static final String TAG = CountryViewModel.class.getSimpleName();
    private CountryRepo countryRepo;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Resource<List<Country>>> mCountryList = new MutableLiveData<>();
    private List<Country> countryItems;


    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepo = CountryRepo.getInstance(application);
    }

    public void searchForCountry(String searchQuery) {
        mCountryList.setValue(Resource.loading(null));

        compositeDisposable.add(
                countryRepo.searchForCountry(searchQuery)
                        .subscribeOn(Schedulers.io())
                        .map(countryGetApiResponse -> {

                            Log.d(TAG, "countries response body : " + countryGetApiResponse.body());

                            if (countryGetApiResponse.body() != null) {
                                Log.d(TAG, "countries size from api : " + countryGetApiResponse.body().size());

                                countryItems = handleCountryResponse(countryGetApiResponse.body());

                                return countryItems;
                            }

                            mCountryList.postValue(Resource.error("no country matching search", null));

                            return countryItems;
                        })
                        .subscribe(
                                countries -> {
                                    if (countryItems != null) {
                                        mCountryList.postValue(Resource.success(countries));
                                    }

                                },
                                throwable -> {
                                    Log.d(TAG, "error searching for country : " + throwable.getLocalizedMessage());
                                    mCountryList.postValue(Resource.error("error searching for item", null));
                                }
                        )
        );
    }

    private List<Country> handleCountryResponse(List<CountryGetApiResponse> data) {
        List<Country> countryList = new ArrayList<>();

        for (CountryGetApiResponse response : data) {
            Country country = new Country();

            country.countryName = response.name;

            country.callingCode = response.callingCodes.get(0);

            country.countryCapital = response.capital;

            country.population = response.population;

            country.timezone = response.timezones.get(0);

            country.language = response.languages.get(0).nativeName;

            country.flag = response.flag;

            country.currency = response.currencies.get(0).code;

            countryList.add(country);
        }

        return countryList;
    }


    public LiveData<Resource<List<Country>>> observeCountries() {
        return mCountryList;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
