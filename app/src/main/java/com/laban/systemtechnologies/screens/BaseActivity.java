package com.laban.systemtechnologies.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.laban.systemtechnologies.errorrs.ErrorRepository;
import com.laban.systemtechnologies.errorrs.ErrorRepositoryLocator;
import com.laban.systemtechnologies.errorrs.exceptions.Error;
import com.laban.systemtechnologies.presentation.ViewModelFactory;
import com.laban.systemtechnologies.screens.error.ErrorDialogFragment;
import com.laban.systemtechnologies.utils.ErrorMessageHelper;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;


public abstract class BaseActivity<T extends BaseViewModel, V extends BaseView> extends AppCompatActivity {
    private T viewModel;
    private boolean rotation = false;
    private Disposable errorDispossible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotation = false;
        viewModel = ((ViewModelFactory) getApplication()).onCreateScreen(getScreen());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
        attachPresenter();
        ErrorRepository repository = ErrorRepositoryLocator.getRepository();
        errorDispossible = repository.getErrorFlow()
                .flatMapSingle(error -> onError(error))
                .subscribe(error -> repository.nextError());
        repository.nextError();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detachPresenter();
        errorDispossible.dispose();
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

    protected Single<Error> onError(Error error) {
        error.setMessage(ErrorMessageHelper.getErrorMessage(error, this));
        ErrorDialogFragment fragment = ErrorDialogFragment.newInstance(error);
        Single<Error> confirmFlow = fragment.getConfirmErrorFlow();
        fragment.show(getSupportFragmentManager().beginTransaction(), "");
        return confirmFlow;
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
