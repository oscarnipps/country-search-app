package com.example.countrysearch;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class CountryRepo {

    private static final String TAG = CountryRepo.class.getSimpleName();
    private static CountryRepo mInstance;
    private CountrySearchService countrySearchService;

    public static CountryRepo getInstance() {
        if (mInstance == null) {

            synchronized (CountryRepo.class) {
                mInstance = new CountryRepo();
            }
        }

        return mInstance;
    }

    private CountryRepo() {
        countrySearchService = ServiceGenerator.createService(CountrySearchService.class);
    }

    public Single<Response<List<CountryGetApiResponse>>> searchForCountry(String searchQuery) {
       return countrySearchService.searchForCountry(searchQuery);
    }
}
