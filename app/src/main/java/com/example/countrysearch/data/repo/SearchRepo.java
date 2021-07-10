package com.example.countrysearch.data.repo;

import android.util.Log;

import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.network.model.CountryGetApiResponse;
import com.example.countrysearch.network.service.CountrySearchService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class SearchRepo {

    private static final String TAG = SearchRepo.class.getSimpleName();
    private CountrySearchService mCountrySearchService;
    private SearchDao mSearchDao;

    @Inject
    public SearchRepo(CountrySearchService countrySearchService, SearchDao searchDao) {
        mCountrySearchService = countrySearchService;
        mSearchDao = searchDao;
    }

    public Single<List<Country>> searchForCountry(String searchQuery) {
       return mCountrySearchService.searchForCountry(searchQuery)
               .map((Response<List<CountryGetApiResponse>> countryGetApiResponse) -> {

                   if (countryGetApiResponse.raw().cacheResponse() != null) {
                       Log.d(TAG, "countries response is from cache ");
                   }

                   if (countryGetApiResponse.raw().networkResponse() != null) {
                       Log.d(TAG, "countries response is from network ");
                   }

                   /*if (countryGetApiResponse.body() != null) {
                       Log.d(TAG, "countries size from api : " + countryGetApiResponse.body().size());

                       //return handleCountryResponse(countryGetApiResponse.body());
                   }*/

                   return handleCountryResponse(countryGetApiResponse.body());
               });
    }

    private List<Country> handleCountryResponse(List<CountryGetApiResponse> data) {
        List<Country> countryList = new ArrayList<>();

        if (data == null) {
            return countryList;
        }

        for (CountryGetApiResponse response : data) {
            Country country = new Country(
                    response.name,
                    response.callingCodes.get(0),
                    response.capital,
                    response.population,
                    response.timezones.get(0),
                    response.languages.get(0).nativeName,
                    response.flag,
                    response.currencies.get(0).code
            );

            countryList.add(country);
        }

        return countryList;
    }

}
