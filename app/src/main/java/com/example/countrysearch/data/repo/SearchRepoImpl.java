package com.example.countrysearch.data.repo;

import androidx.lifecycle.LiveData;

import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.network.model.CountryGetApiResponse;
import com.example.countrysearch.network.service.CountrySearchService;
import com.example.countrysearch.util.CountryMapperUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class SearchRepoImpl implements SearchRepo {

    private static final String TAG = SearchRepoImpl.class.getSimpleName();
    private CountrySearchService mCountrySearchService;
    private SearchDao mSearchDao;

    @Inject
    public SearchRepoImpl(CountrySearchService countrySearchService, SearchDao searchDao) {
        mCountrySearchService = countrySearchService;
        mSearchDao = searchDao;
    }

    @Override
    public Single<List<Country>> searchForCountry(String searchQuery) {
        return mCountrySearchService.searchForCountry(searchQuery)
                .map((Response<List<CountryGetApiResponse>> countryGetApiResponse) -> {

                    if (countryGetApiResponse.raw().cacheResponse() != null) {
                        //Log.d(TAG, "countries response is from cache ");
                    }

                    if (countryGetApiResponse.raw().networkResponse() != null) {
                        //Log.d(TAG, "countries response is from network ");
                    }

                    if (countryGetApiResponse.body() != null) {
                        //Log.d(TAG, "countries size from api : " + countryGetApiResponse.body().size());

                    }

                    return CountryMapperUtil.getCountryListFromApiResponse(countryGetApiResponse.body());
                });
    }

    @Override
    public LiveData<List<Country>> getCountryList() {
        return mSearchDao.getCountryList();
    }

}
