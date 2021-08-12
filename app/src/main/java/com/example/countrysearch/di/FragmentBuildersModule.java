package com.example.countrysearch.di;

import com.example.countrysearch.di.details.DetailsModule;
import com.example.countrysearch.di.details.DetailsViewModelModule;
import com.example.countrysearch.di.favorite.FavoriteModule;
import com.example.countrysearch.di.favorite.FavoriteViewModelModule;
import com.example.countrysearch.di.search.SearchModule;
import com.example.countrysearch.di.search.SearchViewModelModule;
import com.example.countrysearch.di.settings.SettingsModule;
import com.example.countrysearch.ui.details.SearchDetailsFragment;
import com.example.countrysearch.ui.favorite.FavoriteFragment;
import com.example.countrysearch.ui.search.SearchFragment;
import com.example.countrysearch.ui.settings.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = {
            SearchModule.class,
            SearchViewModelModule.class
    })
    public abstract SearchFragment contributeSearchFragment();

    @ContributesAndroidInjector(modules = {
            FavoriteModule.class,
            FavoriteViewModelModule.class
    })
    public abstract FavoriteFragment contributeFavoriteFragment();


    @ContributesAndroidInjector(modules = {
            DetailsModule.class,
            DetailsViewModelModule.class
    })
    public abstract SearchDetailsFragment contributeDetailsFragment();

    @ContributesAndroidInjector(modules = {
            SettingsModule.class
    })
    public abstract SettingsFragment contributeSettingsFragment();
}
