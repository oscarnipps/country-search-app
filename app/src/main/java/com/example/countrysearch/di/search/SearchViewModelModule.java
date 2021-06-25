package com.example.countrysearch.di.search;

import androidx.lifecycle.ViewModel;

import com.example.countrysearch.ui.search.SearchViewModel;
import com.example.countrysearch.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    public abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);


}
