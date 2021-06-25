package com.example.countrysearch.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrysearch.R;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.databinding.FragmentFavoriteBinding;
import com.example.countrysearch.di.ViewModelProviderFactory;
import com.example.countrysearch.ui.search.SearchItemAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavoriteFragment extends DaggerFragment {

    private static final String TAG = FavoriteFragment.class.getSimpleName();
    private FragmentFavoriteBinding binding;
    private NavController navController;
    private FavoriteViewModel favoriteViewModel;
    private RecyclerView mRecyclerview;
    private SearchItemAdapter mSearchItemAdapter;
    private List<Country> mCountryList;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

/*    @Inject
    @Named("Favorite")
    String favoriteName;*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);

        //Log.d(TAG, "module name : " + favoriteName);

        favoriteViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(FavoriteViewModel.class);

        return binding.getRoot();
    }
}
