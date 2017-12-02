package io.kimo.savings.ui.list;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.ui.BaseContract;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public class SavingsListPresenterTest {

    private SavingsListViewModel mViewModel;
    private SavingsService mSavingsService;

    @Before
    public void setUp() throws Exception {
        mViewModel = new SavingsListViewModel(Mockito.mock(Context.class), new SavingsListAdapter());
        mSavingsService = Mockito.mock(SavingsService.class);

        RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        RxJavaHooks.reset();
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void shouldDisplayErrorState() throws Exception {
        Mockito.when(mSavingsService.getSavings()).thenReturn(Observable.error(new NullPointerException()));

        SavingsListPresenter presenter = new SavingsListPresenter(mViewModel, mSavingsService);
        presenter.createView();

        Assert.assertEquals(BaseContract.ViewModel.State.ERROR, mViewModel.getState());
    }

    @Test
    public void shouldDisplayNormalState() throws Exception {
        Saving saving = new Saving();
        saving.setName("name");
        saving.setImageUrl("http://qwerty.asdfgh.zxcvb");
        saving.setCurrentAmount("123");
        saving.setTargetAmount("456");

        Mockito.when(mSavingsService.getSavings()).thenReturn(Observable.just(saving));

        SavingsListPresenter presenter = new SavingsListPresenter(mViewModel, mSavingsService);
        presenter.createView();

        Assert.assertEquals(BaseContract.ViewModel.State.NORMAL, mViewModel.getState());
    }
}