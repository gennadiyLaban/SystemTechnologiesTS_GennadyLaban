package com.laban.systemtechnologies.screens;

import android.util.Log;

public abstract class BaseViewModel<R extends DataRepository, V extends BaseView> {
    private R dataRepository;
    private V view;
    private boolean attach;

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

    protected boolean isAttach() {
        return attach;
    }

    public void attachView(V view) {
        Log.d(getClass().getSimpleName(), "attach " + view.getClass().getSimpleName());
        this.view = view;
        attach = true;
    }

    public void detachView() {
        Log.d(getClass().getSimpleName(), "detach " + view.getClass().getSimpleName());
        this.attach = false;
        this.view = null;
    }

}
