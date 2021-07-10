package com.example.countrysearch.ui.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrysearch.data.Resource;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.data.repo.SearchRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    private static final String TAG = SearchViewModel.class.getSimpleName();
    private SearchRepo mSearchRepo;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Resource<List<Country>>> mCountryList = new MutableLiveData<>();
    private List<Country> countryItems;


    @Inject
    public SearchViewModel(SearchRepo searchRepo, String name) {
        Log.d(TAG, "module name : " + name);
        mSearchRepo = searchRepo;
    }

    public void searchForCountry(String searchQuery) {
        mCountryList.setValue(Resource.loading(null));

        compositeDisposable.add(
                mSearchRepo.searchForCountry(searchQuery)
                        .subscribeOn(Schedulers.io())
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
