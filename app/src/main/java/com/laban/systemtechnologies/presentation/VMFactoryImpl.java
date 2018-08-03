package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.CurrencyDataRepository;
import com.laban.systemtechnologies.screens.currency.CurrencyViewModel;
import com.laban.systemtechnologies.screens.main.MainDataRepository;
import com.laban.systemtechnologies.screens.main.MainViewModel;
import com.laban.systemtechnologies.screens.settings.SettingsDataRepository;
import com.laban.systemtechnologies.screens.settings.SettingsViewModel;

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
        holder.incrementRef();
        return (T) holder.getViewModel();
    }

    @Override
    public void onDestroyScreen(Screen screen) {
        VMHolder holder = getVMHolder(screen);
        if (holder.decrementRef() == 0) {
            removeVMHolder(screen);
        }
    }

    private void removeVMHolder(Screen screen) {
        modelMap.remove(screen);
    }

    private VMHolder getVMHolder(Screen screen) {
        VMHolder holder = modelMap.get(screen);
        if (holder == null) {
            holder = new VMHolder(createViewModel(screen));
            modelMap.put(screen, holder);
        }
        return holder;
    }

    private BaseViewModel createViewModel(Screen screen) {
        BaseViewModel viewModel = null;
        switch (screen) {
            case MAIN:
                viewModel = new MainViewModel((MainDataRepository) repositoryFactory.createRepository(screen));
                break;
            case CURRENCY_LIST:
                viewModel = new CurrencyViewModel((CurrencyDataRepository) repositoryFactory.createRepository(screen));
                break;
            case SETTINGS:
                viewModel = new SettingsViewModel((SettingsDataRepository) repositoryFactory.createRepository(screen));
                break;
        }
        return viewModel;
    }

}
