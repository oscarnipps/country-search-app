package com.example.countrysearch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrysearch.databinding.FragmentSearchBinding;

import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding binding;
    private NavController navController;
    private CountryViewModel countryViewModel;
    private RecyclerView mRecyclerview;
    private CountryAdapter mCountryAdapter;
    private List<Country> mCountryList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        binding.titleTag.setOnClickListener(v -> navController.navigate(R.id.action_searchFragment_to_searchDetailsFragment));

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        binding.setModel(countryViewModel);

        binding.setLifecycleOwner(this);

        setUpRecyclerView();

        return binding.getRoot();
    }

    private void setUpRecyclerView() {
        mRecyclerview = binding.itemRecyclerView;

        mCountryAdapter = new CountryAdapter(mCountryList);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));

        mRecyclerview.setAdapter(mCountryAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        setUpSearchField();

        setUpObservers();
    }

    private void setUpObservers() {
        countryViewModel.observeCountries().observe(getViewLifecycleOwner(), countries -> {
            if (countries != null) {

                switch (countries.status) {

                    case LOADING:
                        binding.loadingView.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        binding.loadingView.setVisibility(View.GONE);
                        mCountryAdapter.setItems(countries.data);
                        break;

                    case ERROR:
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
                Log.d(TAG, "search query : ");
                countryViewModel.searchForCountry(query);
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