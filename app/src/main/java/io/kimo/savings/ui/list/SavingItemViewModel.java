package io.kimo.savings.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;

import io.kimo.savings.R;
import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.ui.BaseViewModel;

public class SavingItemViewModel extends BaseViewModel implements SavingsListContract.SavingItemViewModel {
    private Saving mModel;

    SavingItemViewModel(Context context) {
        super(context);
    }

    @Override
    public void bindModel(@NonNull Saving saving) {
        mModel = saving;
        notifyChange();
    }

    @Override
    public String getName() {
        return mModel.getName();
    }

    @Override
    public String getAmountText() {
        if (!mModel.getTargetAmount().equals("null") ||
                mModel.getTargetAmount().equals("") ||
                mModel.getTargetAmount() == null) {
            return mContext.getString(R.string.item_list_savings_amount_text_complete, mModel.getCurrentAmount(), mModel.getTargetAmount());
        }
        return mContext.getString(R.string.item_list_savings_amount_text_incomplete, mModel.getCurrentAmount());
    }

    @Override
    public String getImageUrl() {
        return mModel.getImageUrl();
    }
}
