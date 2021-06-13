package com.example.countrysearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

public class NetworkConnection extends LiveData<Boolean> {

    public static final String TAG = NetworkConnection.class.getSimpleName();
    private ConnectivityManager.NetworkCallback networkCallback;
    private ConnectivityManager connectivityManager;


    public NetworkConnection(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpNetworkCallback();
            return;
        }

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        Log.d(TAG, "network info : " + networkInfo);

        switch (networkInfo.getType()) {

            case ConnectivityManager.TYPE_WIFI:
                Log.d(TAG, "network info wifi is connected");
                postValue(true);
                break;

            case ConnectivityManager.TYPE_MOBILE:
                Log.d(TAG, "network info mobile is connected");
                postValue(true);
                break;

            default:
                Log.d(TAG, "not network is connected");
                postValue(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpNetworkCallback() {
        networkCallback = getNetworkCallback();

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ConnectivityManager.NetworkCallback getNetworkCallback() {

        return new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                Log.d(TAG, "network available...");
                postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                Log.d(TAG, "network lost...");
                postValue(false);
            }

            @Override
            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                Log.d(TAG, "capability changed : " + networkCapabilities.toString());
            }
        };
    }


    @Override
    protected void onInactive() {
        super.onInactive();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }

        networkCallback = null;
    }

}
