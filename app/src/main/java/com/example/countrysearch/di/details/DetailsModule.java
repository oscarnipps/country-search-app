package com.example.countrysearch.di.details;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @Provides
    static String detailsName() {
        return "details module";
    }
}
