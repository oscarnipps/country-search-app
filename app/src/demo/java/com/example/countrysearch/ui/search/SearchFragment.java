package com.example.countrysearch.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrysearch.R;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.databinding.DemoDialogBinding;
import com.example.countrysearch.databinding.FragmentSearchBinding;
import com.example.countrysearch.di.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SearchFragment extends DaggerFragment {

    private static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding binding;
    private NavController navController;
    private SearchViewModel searchViewModel;
    private RecyclerView mRecyclerview;
    private SearchItemAdapter mSearchItemAdapter;
    private List<Country> mCountryList;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        searchViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(SearchViewModel.class);

        binding.favorites.setOnClickListener(v -> showDemoDialog());

        binding.setModel(searchViewModel);

        binding.setLifecycleOwner(this);

        setUpRecyclerView();

        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        mRecyclerview = binding.itemRecyclerView;

        mSearchItemAdapter = new SearchItemAdapter(mCountryList);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));

        mRecyclerview.setAdapter(mSearchItemAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        setUpSearchField();

        setUpObservers();
    }

    private void showDemoDialog() {
        DemoDialogBinding dialogBinding = DataBindingUtil.inflate(
                LayoutInflater.from(requireContext()),
                R.layout.demo_dialog,
                null,
                false
        );

        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setView(dialogBinding.getRoot())
                .setCancelable(true)
                .create();

        dialog.show();

        dialogBinding.okay.setOnClickListener(v -> dialog.dismiss());
    }

    private void setUpObservers() {
        searchViewModel.observeCountries().observe(getViewLifecycleOwner(), countries -> {

            if (countries != null) {

                switch (countries.status) {

                    case LOADING:
                        binding.loadingView.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        binding.loadingView.setVisibility(View.GONE);
                        binding.errorView.setVisibility(View.GONE);
                        mSearchItemAdapter.setItems(countries.data);
                        break;

                    case ERROR:
                        binding.loadingView.setVisibility(View.GONE);
                        binding.errorView.setVisibility(View.VISIBLE);
                        binding.errorView.setText(countries.message);
                        break;

                }
            }
        });
    }

    private void setUpSearchField() {
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "search query : " + query);
                searchViewModel.searchForCountry(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //Log.d(TAG, "on search query changed : " + query);
                return false;
            }
        });
    }
}