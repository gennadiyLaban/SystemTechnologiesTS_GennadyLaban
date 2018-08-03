package com.laban.systemtechnologies.screens;

public abstract class BaseViewModel {
    private DataRepository dataRepository;
    private BaseView view;

    public BaseViewModel(DataRepository dataRepository, BaseView view) {
        this.dataRepository = dataRepository;
        this.view = view;
    }

    protected DataRepository getDataRepository(){
        return dataRepository;
    }

    protected BaseView getView() {
        return view;
    }

}
