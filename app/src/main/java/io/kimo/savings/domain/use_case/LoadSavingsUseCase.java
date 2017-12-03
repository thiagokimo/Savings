package io.kimo.savings.domain.use_case;


import android.support.annotation.NonNull;

import java.util.List;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.domain.BaseUseCase;
import io.kimo.savings.domain.model.Saving;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoadSavingsUseCase extends BaseUseCase<List<Saving>> {

    private SavingsService mSavingsService;
    private Observable<List<Saving>> mLoadSavingsObservable;
    private Subscription mUseCaseSubscription;

    public LoadSavingsUseCase(@NonNull SavingsService savingsService) {
        mSavingsService = savingsService;
        setupObservables();
    }

    private void setupObservables() {
        mLoadSavingsObservable = mSavingsService.getSavings()
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void execute() {
        if (mCallback != null) {
            mUseCaseSubscription = mLoadSavingsObservable.subscribe(mCallback::onSuccess, mCallback::onError);
        } else {
            mUseCaseSubscription = mLoadSavingsObservable.subscribe();
        }
    }

    @Override
    public void cancel() {
        if (mUseCaseSubscription != null) {
            mUseCaseSubscription.unsubscribe();
        }
    }

    @Override
    public void disconnect() {
        if (mUseCaseSubscription != null && !mUseCaseSubscription.isUnsubscribed()) {
            mUseCaseSubscription.unsubscribe();
        }
    }
}
