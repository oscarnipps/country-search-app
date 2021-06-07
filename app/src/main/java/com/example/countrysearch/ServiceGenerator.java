package com.example.countrysearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //customize to exclude fields
    private static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    //use single instance of the converter factory
    private static GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    //customize builder as required i.e add logging or network interceptor
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constant.API_BASE_URL)
            .client(okHttpClient.build())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
