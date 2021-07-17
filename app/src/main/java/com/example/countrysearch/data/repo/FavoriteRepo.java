package com.example.countrysearch.data.repo;

import android.content.Context;

import com.example.countrysearch.data.dao.FavoriteDao;
import com.example.countrysearch.network.service.FavoriteService;

import javax.inject.Inject;

public class FavoriteRepo {

    private static final String TAG = SearchRepoImpl.class.getSimpleName();
    private static FavoriteRepo mInstance;
    private Context mContext;
    private FavoriteService mFavoriteService;
    private FavoriteDao mFavoriteDao;

    @Inject
    public FavoriteRepo(FavoriteService favoriteService ,FavoriteDao favoriteDao) {
        mFavoriteDao = favoriteDao;
        mFavoriteService = favoriteService;
    }
}
