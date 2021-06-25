package com.example.countrysearch.di;

import com.example.countrysearch.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    FragmentBuildersModule.class,
            })
    abstract MainActivity contributeMainActivity();
}
