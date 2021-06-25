package com.example.countrysearch.di;

import android.app.Application;

import com.example.countrysearch.data.Constants;
import com.example.countrysearch.network.interceptors.CacheInterceptor;
import com.example.countrysearch.network.interceptors.CountryNetworkInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    static Retrofit providesRetrofit(OkHttpClient okHttpClient ,GsonConverterFactory gsonConverterFactory ) {
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .build();
    }


    @Singleton
    @Provides
    static OkHttpClient providesOkHttp(Application appContext, List<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        //cache size of 10mb
        long cacheSize = 10 * 1024 * 1024;

        Cache cache = new Cache(appContext.getCacheDir(), cacheSize);

        return builder.readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    static List<Interceptor> provideInterceptors(Application appContext) {
        List<Interceptor> interceptors = new ArrayList<>();

        interceptors.add(new CacheInterceptor(appContext));

        interceptors.add(new CountryNetworkInterceptor());

        //todo: add logging only if the build version is debug
        interceptors.add(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY));

        return interceptors;
    }

    @Singleton
    @Provides
    static GsonConverterFactory provideGsonConverterFactory() {
        //customize to exclude fields
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return GsonConverterFactory.create(gson);
    }

}
