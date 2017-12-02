package io.kimo.savings.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.kimo.savings.BR;

public abstract class BaseView<P extends BaseContract.Presenter, VM extends BaseContract.ViewModel, VDB extends ViewDataBinding> extends Fragment implements BaseContract.View<P, VM> {

    protected P mPresenter;
    protected VM mViewModel;
    protected VDB mBinding;

    protected abstract VDB inflateDataBinding(LayoutInflater inflater, ViewGroup container);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = inflateDataBinding(inflater, container);
        mBinding.setVariable(BR.presenter, mPresenter);
        mBinding.setVariable(BR.viewModel, mViewModel);
        mBinding.executePendingBindings();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.createView();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.destroyView();
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setViewModel(VM viewModel) {
        mViewModel = viewModel;
    }
}