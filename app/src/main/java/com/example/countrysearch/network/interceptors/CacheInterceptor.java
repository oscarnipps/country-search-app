package com.example.countrysearch.network.interceptors;

import android.content.Context;
import android.util.Log;

import com.example.countrysearch.util.NetworkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {
    public static final String TAG = CacheInterceptor.class.getSimpleName();
    private Context context;

    public CacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        boolean isNetworkConnected = NetworkUtil.isNetworkConnected(context);

        Log.d(TAG, "network connected : " + isNetworkConnected);

        request = getRequestBasedOnNetworkAvailability(isNetworkConnected, request);

        return chain.proceed(request);
    }

    private Request getRequestBasedOnNetworkAvailability(boolean isNetworkConnected, Request request) {

        /*
         * if there is internet get the cache that was stored just 10 seconds ago o
         */
        if (isNetworkConnected) {

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(10, TimeUnit.SECONDS)
                    .build();

            Log.d(TAG, "cache control value  : " + cacheControl.toString());

            return request.newBuilder()
                    .addHeader("Cache-Control", cacheControl.toString())
                    .build();
        }

        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(10, TimeUnit.SECONDS)
                .maxStale(15,TimeUnit.SECONDS)
                .onlyIfCached()
                .build();

        Log.d(TAG, "cache control value  : " + cacheControl.toString());

        return request.newBuilder()
                .addHeader("Cache-Control", cacheControl.toString())
                .build();
    }
}
