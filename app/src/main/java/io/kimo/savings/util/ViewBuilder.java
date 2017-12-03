package io.kimo.savings.util;

import android.content.Context;
import android.support.annotation.NonNull;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.domain.use_case.LoadSavingsUseCase;
import io.kimo.savings.ui.list.SavingsListAdapter;
import io.kimo.savings.ui.list.SavingsListPresenter;
import io.kimo.savings.ui.list.SavingsListView;
import io.kimo.savings.ui.list.SavingsListViewModel;

public class ViewBuilder {
    public static SavingsListView buildSavingsListView(@NonNull Context context) {
        SavingsListViewModel viewModel = new SavingsListViewModel(context, new SavingsListAdapter());

        LoadSavingsUseCase loadSavingsUseCase = new LoadSavingsUseCase(new SavingsService(Injection.okHttpClient()));
        SavingsListPresenter presenter = new SavingsListPresenter(viewModel, loadSavingsUseCase);

        loadSavingsUseCase.setCallback(presenter);

        SavingsListView view = new SavingsListView();

        view.setViewModel(viewModel);
        view.setPresenter(presenter);

        return view;
    }

}
