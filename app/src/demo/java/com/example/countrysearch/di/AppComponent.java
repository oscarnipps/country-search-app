package com.example.countrysearch.di;

import android.app.Application;

import com.example.countrysearch.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class,
        AppModule.class,
        DatabaseModule.class,
        NetworkModule.class,
        ViewModelFactoryModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    //override the default builder
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
