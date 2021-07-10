package com.example.countrysearch.di;

import android.app.Application;

import androidx.room.Room;

import com.example.countrysearch.data.AppDatabase;
import com.example.countrysearch.data.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    static AppDatabase provideDatabase(Application context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                Constants.DATABASE_NAME
        ).build();
    }


}
