package com.example.countrysearch;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.countrysearch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NetworkConnection networkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        networkConnection = new NetworkConnection(this);

        networkConnection.observe(this,isConnected -> {
            Log.d(TAG, "is network connected : " + isConnected);
            binding.offlineIndicator.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        });
    }

}