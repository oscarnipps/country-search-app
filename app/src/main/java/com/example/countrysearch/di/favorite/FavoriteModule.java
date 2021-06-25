package com.example.countrysearch.di.favorite;

import com.example.countrysearch.data.AppDatabase;
import com.example.countrysearch.data.dao.FavoriteDao;
import com.example.countrysearch.data.repo.FavoriteRepo;
import com.example.countrysearch.network.service.FavoriteService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FavoriteModule {

    @Provides
    static String favoriteName() {
        return "favorite module";
    }

    @Provides
    static FavoriteRepo provideFavoriteRepo(FavoriteService favoriteService, FavoriteDao favoriteDao) {
        return new FavoriteRepo(favoriteService, favoriteDao);
    }

    @Provides
    static FavoriteDao provideFavoriteDao(AppDatabase database) {
        return database.favoriteDao();
    }

    @Provides
    static FavoriteService provideFavoriteService(Retrofit retrofit) {
        return retrofit.create(FavoriteService.class);
    }
}
