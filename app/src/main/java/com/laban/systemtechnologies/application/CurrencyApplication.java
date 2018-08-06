package com.laban.systemtechnologies.application;

import android.annotation.SuppressLint;
import android.app.Application;

import com.laban.systemtechnologies.currency.CurrencyPresenter;
import com.laban.systemtechnologies.currency.database.CurrencyHolder;
import com.laban.systemtechnologies.currency.http.CurrencyLoader;
import com.laban.systemtechnologies.errorrs.ErrorRepository;
import com.laban.systemtechnologies.errorrs.ErrorRepositoryLocator;
import com.laban.systemtechnologies.model.DataManager;
import com.laban.systemtechnologies.presentation.DataRepositoryFactoryImpl;
import com.laban.systemtechnologies.presentation.VMFactoryImpl;
import com.laban.systemtechnologies.presentation.ViewModelFactory;
import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.settings.WorkModeHolder;
import com.laban.systemtechnologies.settings.WorkModeHolderLocator;
import com.laban.systemtechnologies.settings.WorkModeManager;

import io.reactivex.schedulers.Schedulers;

public class CurrencyApplication extends Application implements ViewModelFactory {
    private ViewModelFactory viewModelFactory;

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        WorkModeHolder workModeHolder = WorkModeManager.newInstance(this);
        WorkModeHolderLocator.setWorkModeHolder(workModeHolder);

        ErrorRepository errorRepository = new ErrorRepository();
        ErrorRepositoryLocator.setRepository(errorRepository);

        CurrencyLoader currencyLoader = new CurrencyLoader();
        currencyLoader.setWorkMode(workModeHolder.getWorkMode());
        workModeHolder.getWorkModeFlow().subscribe(currencyLoader::setWorkMode);

        CurrencyPresenter currencyPresenter = new CurrencyPresenter(currencyLoader, CurrencyHolder.newInstance(this));
        currencyPresenter.getErrorFlow().subscribeOn(Schedulers.io()).subscribe(errorRepository::addError);

        DataManager dataManager = new DataManager(currencyPresenter, workModeHolder);
        viewModelFactory = new VMFactoryImpl(new DataRepositoryFactoryImpl(dataManager));
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
