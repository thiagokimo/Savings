package io.kimo.savings.util;

import android.content.Context;
import android.support.annotation.NonNull;

import io.kimo.savings.data.SavingsService;
import io.kimo.savings.ui.list.SavingsListAdapter;
import io.kimo.savings.ui.list.SavingsListPresenter;
import io.kimo.savings.ui.list.SavingsListView;
import io.kimo.savings.ui.list.SavingsListViewModel;

public class ViewBuilder {
    public static SavingsListView buildSavingsListView(@NonNull Context context) {
        SavingsListViewModel viewModel = new SavingsListViewModel(context, new SavingsListAdapter());
        SavingsListPresenter presenter = new SavingsListPresenter(viewModel, new SavingsService(Injection.okHttpClient()));
        SavingsListView view = new SavingsListView();

        view.setViewModel(viewModel);
        view.setPresenter(presenter);

        return view;
    }

}
