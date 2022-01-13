package com.example.countrysearch.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.example.countrysearch.R;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.databinding.FragmentSearchDetailsBinding;

import dagger.android.support.DaggerFragment;

public class SearchDetailsFragment extends DaggerFragment {

    private FragmentSearchDetailsBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_details, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.back.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        Bundle args = getArguments();

        if (args != null) {

            Country country = args.getParcelable("country-item");

            if (country != null) {
                mBinding.capitalValue.setText(country.getCountryCapital());

                mBinding.currencyValue.setText(country.getCurrency());

                mBinding.countryNameValue.setText(country.getCountryName());
            }

        }
    }
}