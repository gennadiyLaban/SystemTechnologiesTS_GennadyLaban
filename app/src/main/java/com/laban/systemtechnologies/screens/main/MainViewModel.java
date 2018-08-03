package com.laban.systemtechnologies.screens.main;

import com.laban.systemtechnologies.screens.BaseView;
import com.laban.systemtechnologies.screens.BaseViewModel;

/**
 * Copyright (c) 03.08.2018 Novacom.by
 * All rights reserved
 * Author: H.Laban
 */
public class MainViewModel extends BaseViewModel {

    public MainViewModel(MainDataRepository dataRepository, BaseView view) {
        super(dataRepository, view);
    }

    @Override
    protected MainDataRepository getDataRepository() {
        return (MainDataRepository) super.getDataRepository();
    }

    @Override
    protected MainView getView() {
        return (MainView) super.getView();
    }
}
