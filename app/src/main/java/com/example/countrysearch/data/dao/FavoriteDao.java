package com.example.countrysearch.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.countrysearch.data.model.Country;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FavoriteDao {

    @Insert
    Single<Long> insertFavorite(Country country);

    @Query("SELECT * FROM Country")
    LiveData<Country> getFavoriteItem();

    @Query("SELECT * FROM Country")
    Single<Country> getFavorite();

    @Update()
    Completable updateFavorite(Country country);
}
