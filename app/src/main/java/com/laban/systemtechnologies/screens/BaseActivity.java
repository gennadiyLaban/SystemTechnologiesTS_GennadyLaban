package com.laban.systemtechnologies.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.laban.systemtechnologies.presentation.ViewModelFactory;


public abstract class BaseActivity<T extends BaseViewModel, V extends BaseView> extends AppCompatActivity {
    private T viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ((ViewModelFactory) getApplication()).onCreateScreen(getScreen());
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.attachView((V) this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewModelFactory) getApplication()).onDestroyScreen(getScreen());
    }

    protected T getViewModel() {
        return viewModel;
    }

    protected abstract Screen getScreen();

}
