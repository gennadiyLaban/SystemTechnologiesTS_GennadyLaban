package com.laban.systemtechnologies.screens;

public abstract class BaseViewModel<R extends DataRepository, V extends BaseView> {
    private R dataRepository;
    private V view;

    public BaseViewModel(R dataRepository) {
        this.dataRepository = dataRepository;
        this.view = view;
    }

    protected R getDataRepository() {
        return dataRepository;
    }

    protected V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

}
