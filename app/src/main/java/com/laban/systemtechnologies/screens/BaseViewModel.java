package com.laban.systemtechnologies.screens;

public abstract class BaseViewModel<R extends DataRepository, V extends BaseView> {
    private R dataRepository;
    private V view;

    public BaseViewModel(R dataRepository, V view) {
        this.dataRepository = dataRepository;
        this.view = view;
    }

    protected R getDataRepository() {
        return dataRepository;
    }

    protected V getView() {
        return view;
    }

}
