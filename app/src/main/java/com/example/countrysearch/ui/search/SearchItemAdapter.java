package com.example.countrysearch.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
    private CountryItemInterface mListener;

    public SearchItemAdapter(List<Country> mItems ,CountryItemInterface listener) {
        this.mItems = mItems;
        this.mListener = listener;
    }

    public void setItems(List<Country> countryItems) {
        this.mItems = countryItems;
        notifyDataSetChanged();
    }

    public interface CountryItemInterface {
        void onCountryItemClicked(Country item);
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

    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CountryListItemBinding mHolderBinding;

        public CountryViewHolder(@NonNull CountryListItemBinding binding) {
            super(binding.getRoot());
            this.mHolderBinding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onCountryItemClicked(mItems.get(getBindingAdapterPosition()));
        }
    }
}
