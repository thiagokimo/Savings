package io.kimo.savings.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.ui.BaseViewModel;

public class SavingsListViewModel extends BaseViewModel implements SavingsListContract.ViewModel {

    private String mErrorReason;

    private SavingsListAdapter mAdapter;

    public SavingsListViewModel(@NonNull Context context, @NonNull SavingsListAdapter adapter) {
        super(context);
        mAdapter = adapter;
    }

    @Override
    public void setErrorReason(@NonNull String errorReason) {
        mErrorReason = errorReason;
        setState(State.ERROR);
    }

    @Override
    public String getErrorReason() {
        return mErrorReason;
    }

    @Override
    public void showSavings(List<Saving> savings) {
        mAdapter.setSavings(savings);
        setState(State.NORMAL);
    }

    @Override
    public SavingsListAdapter getAdapter() {
        return mAdapter;
    }
}
