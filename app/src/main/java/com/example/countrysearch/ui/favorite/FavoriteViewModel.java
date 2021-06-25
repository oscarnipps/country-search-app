package com.example.countrysearch.ui.favorite;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class FavoriteViewModel extends ViewModel {
    public static final String TAG = FavoriteViewModel.class.getSimpleName();

    @Inject
    public FavoriteViewModel(String name) {
        Log.d(TAG, "module name : " + name);
    }
}
