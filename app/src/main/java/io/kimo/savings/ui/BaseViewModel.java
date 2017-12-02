package io.kimo.savings.ui;

import android.content.Context;
import android.databinding.BaseObservable;

public class BaseViewModel extends BaseObservable implements BaseContract.ViewModel {
    protected Context mContext;
    private State mState = State.LOADING;

    public BaseViewModel(Context context) {
        mContext = context;
    }

    @Override
    public void setState(State state) {
        mState = state;
        notifyChange();
    }

    @Override
    public State getState() {
        return mState;
    }
}
