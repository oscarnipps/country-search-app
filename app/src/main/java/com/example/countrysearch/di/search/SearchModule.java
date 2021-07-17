package com.example.countrysearch.di.search;

import com.example.countrysearch.data.AppDatabase;
import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.data.repo.SearchRepo;
import com.example.countrysearch.data.repo.SearchRepoImpl;
import com.example.countrysearch.network.service.CountrySearchService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SearchModule {

    @Provides
    static String searchName() {
        return "search module";
    }

    @Provides
    static SearchRepo provideSearchRepo(CountrySearchService searchService, SearchDao searchDao) {
        return new SearchRepoImpl(searchService, searchDao);
    }

    @Provides
    static SearchDao provideSearchDao(AppDatabase database) {
        return database.searchDao();
    }

    @Provides
    static CountrySearchService provideCountrySearchService(Retrofit retrofit) {
        return retrofit.create(CountrySearchService.class);
    }

}
