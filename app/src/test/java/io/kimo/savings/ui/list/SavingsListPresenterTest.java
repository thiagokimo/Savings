package io.kimo.savings.ui.list;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.domain.model.Saving;
import io.kimo.savings.domain.use_case.LoadSavingsUseCase;
import io.kimo.savings.ui.BaseContract;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

public class SavingsListPresenterTest {
    @Before
    public void setUp() throws Exception {
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
        SavingsService savingsService = Mockito.mock(SavingsService.class);
        Mockito.when(savingsService.getSavings()).thenReturn(Observable.error(new Throwable()));

        LoadSavingsUseCase loadSavingsUseCase = new LoadSavingsUseCase(savingsService);
        SavingsListViewModel viewModel = new SavingsListViewModel(Mockito.mock(Context.class), new SavingsListAdapter());
        SavingsListPresenter presenter = new SavingsListPresenter(viewModel, loadSavingsUseCase);
        loadSavingsUseCase.setCallback(presenter);

        presenter.createView();

        Assert.assertEquals(BaseContract.ViewModel.State.ERROR, viewModel.getState());
    }

    @Test
    public void shouldDisplayNormalState() throws Exception {
        SavingsService savingsService = Mockito.mock(SavingsService.class);
        Mockito.when(savingsService.getSavings()).thenReturn(Observable.just(new Saving()));

        LoadSavingsUseCase loadSavingsUseCase = new LoadSavingsUseCase(savingsService);
        SavingsListViewModel viewModel = new SavingsListViewModel(Mockito.mock(Context.class), new SavingsListAdapter());
        SavingsListPresenter presenter = new SavingsListPresenter(viewModel, loadSavingsUseCase);
        loadSavingsUseCase.setCallback(presenter);

        presenter.createView();

        Assert.assertEquals(BaseContract.ViewModel.State.NORMAL, viewModel.getState());
    }
}