package com.example.countrysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.countrysearch.databinding.CountryListItemBinding;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    public static final String TAG = CountryAdapter.class.getSimpleName();
    private List<Country> mItems;
    private CountryListItemBinding mItemBinding;
    private Context mContext;

    public CountryAdapter(List<Country> mItems) {
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
        holder.mHolderBinding.capital.setText(mItems.get(position).countryCapital);

        holder.mHolderBinding.population.setText(String.valueOf(mItems.get(position).population));

        holder.mHolderBinding.name.setText(String.valueOf(mItems.get(position).countryName));

        //glide to show country name
        Glide.with(mContext).load(mItems.get(position).flag).into(mItemBinding.flag);
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
