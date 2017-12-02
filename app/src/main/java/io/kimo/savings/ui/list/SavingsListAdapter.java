package io.kimo.savings.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.kimo.savings.databinding.SavingItemLayoutBinding;
import io.kimo.savings.domain.model.Saving;

public class SavingsListAdapter extends RecyclerView.Adapter<SavingsListAdapter.ViewHolder> {

    private List<Saving> mData = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(SavingItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void setSavings(@NonNull List<Saving> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Saving saving = mData.get(position);
        holder.bind(saving);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final SavingItemLayoutBinding binding;

        ViewHolder(SavingItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Saving saving) {
            SavingItemViewModel viewModel = new SavingItemViewModel(binding.getRoot().getContext());
            binding.setViewModel(viewModel);
            binding.getViewModel().bindModel(saving);
        }
    }
}
