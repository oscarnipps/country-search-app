package com.example.countrysearch.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrysearch.R;
import com.example.countrysearch.data.model.Country;
import com.example.countrysearch.databinding.CountryListItemBinding;

import java.util.List;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.CountryViewHolder> {

    public static final String TAG = SearchItemAdapter.class.getSimpleName();
    private List<Country> mItems;
    private CountryListItemBinding mItemBinding;
    private Context mContext;

    public SearchItemAdapter(List<Country> mItems) {
        this.mItems = mItems;
    }

    public void setItems(List<Country> countryItems) {
        this.mItems = countryItems;
        notifyDataSetChanged();
    }

    public interface CountryItemInterface {
        void onCountryItemClicked();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.country_list_item, parent, false);
        mContext = parent.getContext();
        return new CountryViewHolder(mItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.mHolderBinding.capital.setText(mItems.get(position).getCountryCapital());

        holder.mHolderBinding.population.setText(String.valueOf(mItems.get(position).getPopulation()));

        holder.mHolderBinding.name.setText(String.valueOf(mItems.get(position).getCountryName()));

        //glide to show country name
        //Glide.with(mContext).load(mItems.get(position).flag).into(mItemBinding.flag);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private CountryListItemBinding mHolderBinding;

        public CountryViewHolder(@NonNull CountryListItemBinding binding) {
            super(binding.getRoot());
            this.mHolderBinding = binding;
        }
    }
}
