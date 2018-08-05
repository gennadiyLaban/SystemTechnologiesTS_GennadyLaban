package com.laban.systemtechnologies.application;

import android.app.Application;

import com.laban.systemtechnologies.currency.http.CurrencyLoader;
import com.laban.systemtechnologies.presentation.DataRepositoryFactoryImpl;
import com.laban.systemtechnologies.presentation.VMFactoryImpl;
import com.laban.systemtechnologies.presentation.ViewModelFactory;
import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.Screen;

public class CurrencyApplication extends Application implements ViewModelFactory {
    private ViewModelFactory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        viewModelFactory = new VMFactoryImpl(new DataRepositoryFactoryImpl(new CurrencyLoader()));
    }

    @Override
    public <T extends BaseViewModel> T onCreateScreen(Screen screen) {
        return viewModelFactory.onCreateScreen(screen);
    }

    @Override
    public void onDestroyScreen(Screen screen, boolean rotation) {
        viewModelFactory.onDestroyScreen(screen, rotation);
    }

}
