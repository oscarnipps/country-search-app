package com.example.countrysearch;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class CountryRepo {

    private static final String TAG = CountryRepo.class.getSimpleName();
    private static CountryRepo mInstance;
    private CountrySearchClient countrySearchClient;

    public static CountryRepo getInstance() {
        if (mInstance == null) {

            synchronized (CountryRepo.class) {
                mInstance = new CountryRepo();
            }
        }

        return mInstance;
    }

    private CountryRepo() {
        countrySearchClient = ServiceGenerator.createService(CountrySearchClient.class);
    }

    public Single<Response<List<CountryGetApiResponse>>> searchForCountry(String searchQuery) {
       return countrySearchClient.searchForCountry(searchQuery);
    }
}
