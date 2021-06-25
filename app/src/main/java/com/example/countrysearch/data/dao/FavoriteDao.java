package com.example.countrysearch.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.countrysearch.data.model.Country;

@Dao
public interface FavoriteDao {

    @Insert
    long insertFavorite(Country country);

    @Query("SELECT * FROM Country")
    LiveData<Country> getFavorites();
}
