package com.laban.systemtechnologies.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {
    private T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected T getViewModel() {
        return viewModel;
    }

}
