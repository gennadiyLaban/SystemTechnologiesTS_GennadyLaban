package com.laban.systemtechnologies.screens.settings.presentation;

import com.laban.systemtechnologies.screens.BaseViewModel;

import io.reactivex.disposables.Disposable;

public class SettingsViewModel extends BaseViewModel<SettingsDataRepository, SettingsView> {
    private Disposable workModeDisposable;

    public SettingsViewModel(SettingsDataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    public void attachView(SettingsView view) {
        super.attachView(view);
        getView().setWorkMode(getDataRepository().getWorkMode());
        workModeDisposable = getView().getWorkModeFlow().subscribe(getDataRepository()::setWorkMode);
    }

    @Override
    public void detachView() {
        super.detachView();
        workModeDisposable.dispose();
    }
}
