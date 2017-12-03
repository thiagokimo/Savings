package io.kimo.savings.ui.list;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.kimo.savings.domain.model.Saving;

public class SavingItemViewModelTest {
    @Mock
    private Context mContext;
    private SavingItemViewModel mViewModel;

    private static final String COMPLETE_AMOUNT_TEXT_FORMAT = "$%s of %s";
    private static final String INCOMPLETE_AMOUNT_TEXT_FORMAT = "$%s";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mViewModel = new SavingItemViewModel(mContext);
    }

    @Test
    public void formatCompleteAmountText() throws Exception {
        Saving saving = new Saving();
        saving.setCurrentAmount("123");
        saving.setTargetAmount("456");

        Mockito.when(mContext.getString(Mockito.anyInt(), Mockito.any(String.class), Mockito.any(String.class)))
                .thenReturn(String.format(COMPLETE_AMOUNT_TEXT_FORMAT, saving.getCurrentAmount(), saving.getTargetAmount()));

        mViewModel.bindModel(saving);

        String result = mViewModel.getAmountText();

        Mockito.verify(mContext).getString(Mockito.anyInt(), Mockito.any(String.class), Mockito.any(String.class));

        Assert.assertEquals("$123 of 456", result);
    }

    @Test
    public void formatIncompleteAmountText() throws Exception {
        Saving saving = new Saving();
        saving.setCurrentAmount("123");

        Mockito.when(mContext.getString(Mockito.anyInt(), Mockito.any(String.class)))
                .thenReturn(String.format(INCOMPLETE_AMOUNT_TEXT_FORMAT, saving.getCurrentAmount()));

        mViewModel.bindModel(saving);

        String result = mViewModel.getAmountText();

        Mockito.verify(mContext).getString(Mockito.anyInt(), Mockito.any(String.class));

        Assert.assertEquals("$123", result);
    }
}