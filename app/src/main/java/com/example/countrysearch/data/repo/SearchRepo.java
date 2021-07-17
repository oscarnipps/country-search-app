package com.example.countrysearch.data.repo;

import androidx.lifecycle.LiveData;

import com.example.countrysearch.data.model.Country;

import java.util.List;

import io.reactivex.Single;

public interface SearchRepo {

    Single<List<Country>> searchForCountry(String searchQuery);

    LiveData<List<Country>> getCountryList();

}
