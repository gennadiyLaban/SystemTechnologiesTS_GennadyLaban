package com.laban.systemtechnologies.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.laban.systemtechnologies.presentation.ViewModelFactory;


public abstract class BaseActivity<T extends BaseViewModel, V extends BaseView> extends AppCompatActivity {
    private T viewModel;
    private boolean rotation = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotation = false;
        viewModel = ((ViewModelFactory) getApplication()).onCreateScreen(getScreen());
    }

    @Override
    protected void onStart() {
        super.onStart();
        attachPresenter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detachPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        rotation = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewModelFactory) getApplication()).onDestroyScreen(getScreen(), rotation);
    }

    protected T getViewModel() {
        return viewModel;
    }

    protected abstract Screen getScreen();

    protected void attachPresenter() {
        viewModel.attachView((V) this);
    }

    protected void detachPresenter() {
        viewModel.detachView();
    }

}
