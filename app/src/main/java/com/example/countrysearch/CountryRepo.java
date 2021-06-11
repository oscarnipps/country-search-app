package com.example.countrysearch;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class CountryRepo {

    private static final String TAG = CountryRepo.class.getSimpleName();
    private static CountryRepo mInstance;
    private CountrySearchService countrySearchService;
    private Context mContext;

    public static CountryRepo getInstance(Context context) {
        if (mInstance == null) {

            synchronized (CountryRepo.class) {
                mInstance = new CountryRepo(context);
            }
        }

        return mInstance;
    }

    private CountryRepo(Context context) {
        mContext = context;
        countrySearchService = ServiceGenerator.getClient(context).create(CountrySearchService.class);
    }

    public Single<Response<List<CountryGetApiResponse>>> searchForCountry(String searchQuery) {
       return countrySearchService.searchForCountry(searchQuery);
    }
}
