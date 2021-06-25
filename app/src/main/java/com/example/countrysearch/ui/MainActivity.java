package com.example.countrysearch.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.countrysearch.R;
import com.example.countrysearch.databinding.ActivityMainBinding;
import com.example.countrysearch.util.NetworkConnectionMonitor;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NetworkConnectionMonitor networkConnectionMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        networkConnectionMonitor = new NetworkConnectionMonitor(this);

        networkConnectionMonitor.observe(this, isConnected -> {
            Log.d(TAG, "is network connected : " + isConnected);
            binding.offlineIndicator.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        });
    }

}