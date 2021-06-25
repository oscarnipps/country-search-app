package com.example.countrysearch.data.repo;

import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.network.model.CountryGetApiResponse;
import com.example.countrysearch.network.service.CountrySearchService;
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

    public Single<Response<List<CountryGetApiResponse>>> searchForCountry(String searchQuery) {
       return mCountrySearchService.searchForCountry(searchQuery);
    }

}
