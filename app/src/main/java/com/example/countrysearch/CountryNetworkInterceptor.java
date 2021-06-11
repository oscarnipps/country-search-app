package com.example.countrysearch;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CountryNetworkInterceptor implements Interceptor {

    public static final String TAG = CountryNetworkInterceptor.class.getSimpleName();

    //cache size of 10MB
    public long cacheSize = 10 * 1024 * 1024;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        //get the request
        Request request = chain.request();

        //get the response from the request
        Response response = chain.proceed(request);

        if (response.code() == 404) {
            //logic to handle when a 401 is retrieved for now just log it
            Log.d(TAG , "response was gotten from the server and was 404");
        }

        return response;
    }

/*    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        //get the request
        Request request = chain.request();

        //get the response from the request
        Response response = chain.proceed(request);


        if (response.code() == 404) {
            //logic to handle when a 401 is retrieved for now just log it
            Log.d(TAG , "response was gotten from the server and was 404");
        }

        return response;
    }*/

}
