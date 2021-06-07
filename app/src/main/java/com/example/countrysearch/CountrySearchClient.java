package com.example.countrysearch;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountrySearchClient {

    @GET("name/{searchQuery}")
    Single<Response<List<CountryGetApiResponse>>> searchForCountry(@Path("searchQuery") String searchQuery);
}
