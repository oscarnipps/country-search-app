package com.example.countrysearch.data.dao;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.countrysearch.LiveDataAndroidTestUtil;
import com.example.countrysearch.data.AppDatabase;
import com.example.countrysearch.data.model.Country;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FavoriteDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private final Country mCountry = new Country(
            1,
            "Targaryen",
            "228",
            "lannister",
            10000,
            "UTC +1",
            "mammoth",
            "cowries",
            "flag targaryen"
    );

    private FavoriteDao mFavoriteDao;


    @Before
    public void initializeDatabase() {
        mDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        mFavoriteDao = mDatabase.favoriteDao();
    }

    @Test
    public void insert_favorite_item_returns_row_id_of_1() {
        long insertResultId = mFavoriteDao.insertFavorite(mCountry).blockingGet();

        assertEquals(1, insertResultId);
    }

    @Test
    public void existing_favorite_item_gets_updated() throws InterruptedException {
        //given item has been added to data source
        mFavoriteDao.insertFavorite(mCountry).subscribe();

        Integer updatedPopulation = 20000;

        String updatedCurrency = "cowries pounds";

        mCountry.setPopulation(updatedPopulation);

        mCountry.setCurrency(updatedCurrency);

        mFavoriteDao.updateFavorite(mCountry).subscribe();

        Country country = LiveDataAndroidTestUtil.getOrAwaitValue(mFavoriteDao.getFavoriteItem());

        assertTrue(country.getCurrency().equals(updatedCurrency) && country.getPopulation().equals(updatedPopulation));

/*
        mFavoriteDao.getFavorite()
                .test()
                .assertValue(country ->
                        country.getCurrency().equals(updatedCurrency) && country.getPopulation().equals(updatedPopulation)
                );
*/
    }


    @After
    public void closeDatabase() {
        mDatabase.close();
    }

}
