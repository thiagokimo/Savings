package io.kimo.savings.ui.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.kimo.savings.databinding.SavingsListLayoutBinding;
import io.kimo.savings.ui.BaseView;

public class SavingsListView extends BaseView<SavingsListContract.Presenter, SavingsListContract.ViewModel, SavingsListLayoutBinding> implements SavingsListContract.View {

    @Override
    protected SavingsListLayoutBinding inflateDataBinding(LayoutInflater inflater, ViewGroup container) {
        SavingsListLayoutBinding binding = SavingsListLayoutBinding.inflate(inflater, container, false);
        configureRecyclerView(binding);
        return binding;
    }

    private void configureRecyclerView(SavingsListLayoutBinding binding) {
        RecyclerView recyclerView = binding.list;

        recyclerView.setAdapter(mViewModel.getAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
