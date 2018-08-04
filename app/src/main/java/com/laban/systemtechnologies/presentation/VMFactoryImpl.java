package com.laban.systemtechnologies.presentation;

import android.util.Log;

import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyViewModel;
import com.laban.systemtechnologies.screens.main.presentation.MainViewModel;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsViewModel;

import java.util.HashMap;
import java.util.Map;

public class VMFactoryImpl implements ViewModelFactory {
    private Map<Screen, VMHolder> modelMap;
    private DataRepositoryFactory repositoryFactory;

    public VMFactoryImpl(DataRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelMap = new HashMap<>();
    }

    @Override
    public <T extends BaseViewModel> T onCreateScreen(Screen screen) {
        VMHolder holder = getVMHolder(screen);
        Log.d(getClass().getSimpleName(), "on_create " + screen.name());
        holder.incrementRef();
        return (T) holder.getViewModel();
    }

    @Override
    public void onDestroyScreen(Screen screen, boolean rotation) {
        VMHolder holder = getVMHolder(screen);
        Log.d(getClass().getSimpleName(), "destroy " + screen.name());
        if (holder.decrementRef() == 0 && !rotation) {
            removeVMHolder(screen);
        }
    }

    private void removeVMHolder(Screen screen) {
        Log.d(getClass().getSimpleName(), "remove " + screen.name());
        modelMap.remove(screen);
    }

    private VMHolder getVMHolder(Screen screen) {
        VMHolder holder = modelMap.get(screen);
        if (holder == null) {
            Log.d(getClass().getSimpleName(), "create " + screen.name());
            holder = new VMHolder(createViewModel(screen));
            modelMap.put(screen, holder);
        }
        return holder;
    }

    private BaseViewModel createViewModel(Screen screen) {
        BaseViewModel viewModel = null;
        switch (screen) {
            case MAIN:
                viewModel = new MainViewModel(repositoryFactory.createRepository(screen));
                break;
            case CURRENCY_LIST:
                viewModel = new CurrencyViewModel(repositoryFactory.createRepository(screen));
                break;
            case SETTINGS:
                viewModel = new SettingsViewModel(repositoryFactory.createRepository(screen));
                break;
        }
        return viewModel;
    }

}
