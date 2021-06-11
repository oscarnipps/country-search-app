package com.example.countrysearch;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (okHttpClient == null) {
            setUpOkHttp(context);
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constant.API_BASE_URL)
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    private static void setUpOkHttp(Context context) {
         okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CountryNetworkInterceptor())
                .addInterceptor(getLoggingInterceptor())
                 .build();
    }

    private static GsonConverterFactory getGsonConverterFactory() {
        //customize to exclude fields
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

       return GsonConverterFactory.create(gson);
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
