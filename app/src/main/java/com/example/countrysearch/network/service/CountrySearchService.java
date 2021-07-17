package com.example.countrysearch.network.service;

import com.example.countrysearch.network.model.CountryGetApiResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountrySearchService {

    @GET("name/{searchQuery}")
    Single<Response<List<CountryGetApiResponse>>> searchForCountry(@Path("searchQuery") String searchQuery);
}
