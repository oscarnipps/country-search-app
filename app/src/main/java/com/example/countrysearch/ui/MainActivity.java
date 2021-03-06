package com.example.countrysearch.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.countrysearch.R;
import com.example.countrysearch.databinding.ActivityMainBinding;
import com.example.countrysearch.util.NetworkConnectionMonitor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private NetworkConnectionMonitor networkConnectionMonitor;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpBottomNavigationView();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_host_container);

        if (navHostFragment != null) {

            navController = navHostFragment.getNavController();

            NavigationUI.setupWithNavController(bottomNavigationView,navController);
        }

        networkConnectionMonitor = new NetworkConnectionMonitor(this);

        networkConnectionMonitor.observe(this, isConnected -> {
            Log.d(TAG, "is network connected : " + isConnected);
            binding.offlineIndicator.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        });
    }

    private void setUpBottomNavigationView() {
        bottomNavigationView = binding.bottomNav;

        //since bottom navigation view does not support shape appearance attribute
        //use the shape appearance model to apply custom shapes
        MaterialShapeDrawable bottomNavViewBackground = (MaterialShapeDrawable) bottomNavigationView.getBackground();

        //todo: get the radius for the corner size from a dimen resource
        bottomNavViewBackground.setShapeAppearanceModel( bottomNavViewBackground.getShapeAppearanceModel()
                .toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED,32)
                .setTopRightCorner(CornerFamily.ROUNDED,32)
                .build()
        );
    }

}