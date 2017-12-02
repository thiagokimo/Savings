package io.kimo.savings.ui;


import android.databinding.Observable;

public interface BaseContract {
    interface View<P extends Presenter, VM extends ViewModel>{
        void setPresenter(P presenter);
        void setViewModel(VM viewModel);
    }

    interface Presenter {
        void createView();
        void destroyView();

        void recoverFromError();
    }

    interface ViewModel extends Observable {
        enum State {
            LOADING, ERROR, NORMAL
        }

        void setState(State state);
        State getState();
    }
}
