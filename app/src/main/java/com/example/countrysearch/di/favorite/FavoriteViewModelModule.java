package com.example.countrysearch.di.favorite;

import androidx.lifecycle.ViewModel;

import com.example.countrysearch.di.ViewModelKey;
import com.example.countrysearch.ui.favorite.FavoriteViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FavoriteViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel.class)
    public abstract ViewModel bindFavoriteViewModel(FavoriteViewModel favoriteViewModel);

}
