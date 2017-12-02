package io.kimo.savings.domain;

public abstract class BaseUseCase<T> {

    public interface Callback<T> {
        void onSuccess(T result);
        void onError(Throwable e);
    }

    protected Callback<T> mCallback;

    public void setCallback(Callback<T> callback) {
        mCallback = callback;
    }

    public Callback<T> getCallback() {
        return mCallback;
    }

    public abstract void execute();
    public abstract void cancel();
    public abstract void disconnect();
}
