package io.kimo.savings.ui.list;

import android.support.annotation.NonNull;

import java.util.List;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.domain.BaseUseCase;
import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.domain.use_case.LoadSavingsUseCase;
import io.kimo.savings.ui.BaseContract;
import io.kimo.savings.ui.BasePresenter;

public class SavingsListPresenter extends BasePresenter<SavingsListContract.ViewModel> implements SavingsListContract.Presenter {

    private SavingsService mSavingsService;
    private LoadSavingsUseCase mLoadSavingsUseCase;

    public SavingsListPresenter(@NonNull SavingsListContract.ViewModel viewModel, @NonNull SavingsService savingsService) {
        super(viewModel);
        mSavingsService = savingsService;
        setupUseCases();
    }

    private void setupUseCases() {
        mLoadSavingsUseCase = new LoadSavingsUseCase(mSavingsService);
        mLoadSavingsUseCase.setCallback(new BaseUseCase.Callback<List<Saving>>() {
            @Override
            public void onSuccess(List<Saving> result) {
                mViewModel.showSavings(result);
                mViewModel.setState(BaseContract.ViewModel.State.NORMAL);
            }

            @Override
            public void onError(Throwable e) {
                mViewModel.setErrorReason(e.getMessage());
                mViewModel.setState(BaseContract.ViewModel.State.ERROR);
            }
        });
    }

    @Override
    public void createView() {
        super.createView();
        mLoadSavingsUseCase.execute();
    }

    @Override
    public void destroyView() {
        mLoadSavingsUseCase.disconnect();
        super.destroyView();
    }

    @Override
    public void recoverFromError() {
        mViewModel.setState(BaseContract.ViewModel.State.LOADING);
        mLoadSavingsUseCase.execute();
    }
}
