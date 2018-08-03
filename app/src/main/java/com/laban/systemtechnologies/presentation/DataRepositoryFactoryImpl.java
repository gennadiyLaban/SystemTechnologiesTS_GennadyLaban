package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.CurrencyDataRepository;
import com.laban.systemtechnologies.screens.main.MainDataRepository;
import com.laban.systemtechnologies.screens.settings.SettingsDataRepository;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {


    @Override
    public <T extends DataRepository> T createRepository(Screen screen) {
        DataRepository dataRepository = null;
        switch (screen) {
            case MAIN:
                dataRepository = new MainDataRepository() {
                };
                break;
            case CURRENCY_LIST:
                dataRepository = new CurrencyDataRepository() {
                };
                break;
            case SETTINGS:
                dataRepository = new SettingsDataRepository() {
                };
                break;
        }
        return (T) dataRepository;
    }
}