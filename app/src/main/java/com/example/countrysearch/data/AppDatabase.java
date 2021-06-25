package com.example.countrysearch.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.countrysearch.data.dao.FavoriteDao;
import com.example.countrysearch.data.dao.SearchDao;
import com.example.countrysearch.data.model.Country;

@Database(version = 1,
        exportSchema = false,entities = {
        Country.class
})
public abstract class AppDatabase extends RoomDatabase {

   public abstract FavoriteDao favoriteDao();

   public abstract SearchDao searchDao();
}
