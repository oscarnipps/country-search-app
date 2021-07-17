package com.example.countrysearch.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.countrysearch.data.model.Country;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface SearchDao {

    @Insert
    Single<Long> insertCountry(Country country);

    @Query("SELECT * FROM Country")
    LiveData<List<Country>> getCountryList();

    @Update()
    Completable updateItem(Country country);
}
