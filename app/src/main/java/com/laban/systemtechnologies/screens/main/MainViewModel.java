package com.laban.systemtechnologies.screens.main;

import com.laban.systemtechnologies.screens.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainDataRepository, MainView> {

    public MainViewModel(MainDataRepository dataRepository) {
        super(dataRepository);
    }

    public void onPressCurrencyBtn() {
        getView().openCurrencyScreen();
    }

    public void onPressSettingsBtn() {
        getView().openSettingsScreen();
    }

}
