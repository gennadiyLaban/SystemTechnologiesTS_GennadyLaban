package com.laban.systemtechnologies.presentation;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.model.DataManager;
import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyDataRepository;
import com.laban.systemtechnologies.screens.main.presentation.MainDataRepository;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsDataRepository;
import com.laban.systemtechnologies.settings.WorkMode;

import java.util.List;

import io.reactivex.Flowable;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {
    private DataManager dataManager;

    public DataRepositoryFactoryImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

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
                    @Override
                    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
                        return dataManager.getCurrencyItemFlow();
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void updateData() {
                        dataManager.updateCurrencyCourses();
                    }

                };
                break;
            case SETTINGS:
                dataRepository = new SettingsDataRepository() {
                    @Override
                    public WorkMode getWorkMode() {
                        return dataManager.getWorkMode();
                    }

                    @Override
                    public void setWorkMode(WorkMode workMode) {
                        dataManager.setWorkMode(workMode);
                    }
                };
                break;
        }
        return (T) dataRepository;
    }
}
