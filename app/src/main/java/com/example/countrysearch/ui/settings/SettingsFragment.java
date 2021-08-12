package com.example.countrysearch.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.countrysearch.R;
import com.example.countrysearch.databinding.FragmentSettingsBinding;
import com.example.countrysearch.databinding.ThemeSelectionDialogBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import dagger.android.support.DaggerFragment;

public class SettingsFragment extends DaggerFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();
    private FragmentSettingsBinding binding;
    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

        binding.setLifecycleOwner(this);

        binding.themeContainer.setOnClickListener(view -> showThemeSelectionDialog());

        return binding.getRoot();
    }

    private void showThemeSelectionDialog() {
        //create the custom dialog layout
        ThemeSelectionDialogBinding dialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireContext()),
                R.layout.theme_selection_dialog,
                null,
                false
        );

        AlertDialog dialog = new MaterialAlertDialogBuilder(requireContext())
                .setView(dialogBinding.getRoot())
                .setCancelable(true)
                .create();

        dialog.show();

        dialogBinding.okay.setOnClickListener(v -> dialog.dismiss());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }
}
