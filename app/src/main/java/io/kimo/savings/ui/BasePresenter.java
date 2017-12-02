package io.kimo.savings.ui;

import android.support.annotation.NonNull;

public abstract class BasePresenter<VM extends BaseContract.ViewModel> implements BaseContract.Presenter {
    @NonNull
    protected final VM mViewModel;

    public BasePresenter(@NonNull VM viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void createView() {}

    @Override
    public void destroyView() {}
}
