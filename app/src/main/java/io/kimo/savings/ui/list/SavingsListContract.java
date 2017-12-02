package io.kimo.savings.ui.list;

import android.support.annotation.NonNull;

import java.util.List;

import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.ui.BaseContract;

public interface SavingsListContract {
    interface View extends BaseContract.View<Presenter, ViewModel> {}
    interface Presenter extends BaseContract.Presenter {}
    interface ViewModel extends BaseContract.ViewModel {
        String getErrorReason();
        void setErrorReason(@NonNull String errorReason);

        void showSavings(List<Saving> savings);

        SavingsListAdapter getAdapter();
    }

    interface SavingItemViewModel extends BaseContract.ViewModel {
        void bindModel(@NonNull Saving saving);
        String getName();
        String getAmountText();
        String getImageUrl();
    }
}
