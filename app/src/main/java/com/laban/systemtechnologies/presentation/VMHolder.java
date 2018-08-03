package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.BaseViewModel;

public class VMHolder {
    private BaseViewModel viewModel;
    private int refCount;

    public VMHolder(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public int incrementRef() {
        return ++refCount;
    }

    public int decrementRef() {
        return --refCount;
    }

    public int getRefCount() {
        return refCount;
    }

    public BaseViewModel getViewModel() {
        return viewModel;
    }
}
