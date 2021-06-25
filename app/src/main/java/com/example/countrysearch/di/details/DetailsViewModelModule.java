package com.example.countrysearch.di.details;

import androidx.lifecycle.ViewModel;

import com.example.countrysearch.di.ViewModelKey;
import com.example.countrysearch.ui.details.DetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    public abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);
}
