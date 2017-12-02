package io.kimo.savings.ui.list;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.kimo.savings.R;
import io.kimo.savings.domain.model.Saving;

@RunWith(MockitoJUnitRunner.class)
public class SavingItemViewModelTest {
    @Mock
    private Context mContext;

    @Test
    public void isFullAmountText() throws Exception {
        MockitoAnnotations.initMocks(this);
        Saving saving = new Saving();
        saving.setName("name");
        saving.setImageUrl("http://qwerty.asdfgh.zxcvb");
        saving.setCurrentAmount("123");
        saving.setTargetAmount("456");

        Mockito.when(mContext.getString(R.string.item_list_savings_amount_text_complete)).thenReturn("$%s of %s", saving.getCurrentAmount(), saving.getTargetAmount());
        Mockito.when(mContext.getString(R.string.item_list_savings_amount_text_incomplete)).thenReturn("$%s", saving.getCurrentAmount());

        SavingItemViewModel viewModel = new SavingItemViewModel(mContext);

        viewModel.bindModel(saving);

        Assert.assertEquals("$123 of 456", viewModel.getAmountText());

        saving.setTargetAmount("null");
        viewModel.bindModel(saving);

        Assert.assertEquals("$123", viewModel.getAmountText());

        saving.setTargetAmount("");
        viewModel.bindModel(saving);

        Assert.assertEquals("$123", viewModel.getAmountText());

        saving.setTargetAmount(null);
        viewModel.bindModel(saving);

        Assert.assertEquals("$123", viewModel.getAmountText());
    }

}