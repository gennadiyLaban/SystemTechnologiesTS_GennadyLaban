package com.laban.systemtechnologies.screens.currency.presentation;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class CurrencyViewModel extends BaseViewModel<CurrencyDataRepository, CurrencyView> {
    private List<CurrencyItem> currencyItems;
    private BehaviorSubject<List<CurrencyItem>> currencyFlow;
    private Disposable newDataDisposable;
    private Disposable updateDisposable;

    public CurrencyViewModel(CurrencyDataRepository dataRepository) {
        super(dataRepository);
        currencyFlow = BehaviorSubject.create();
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView(CurrencyView view) {
        super.attachView(view);
        updateDisposable = getView().updateDataFlow().subscribe(o -> getDataRepository().updateData());
        newDataDisposable = getDataRepository().getCurrencyItemFlow().doOnNext(this::updateCache).filter(items -> isAttach()).subscribe(currencyFlow::onNext);

        if (currencyItems == null) {
            getDataRepository().updateData();
        } else {
            currencyFlow.onNext(currencyItems);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (newDataDisposable != null && !newDataDisposable.isDisposed()) {
            newDataDisposable.dispose();
        }
        if (updateDisposable != null && !updateDisposable.isDisposed()) {
            updateDisposable.dispose();
        }
        currencyFlow.onComplete();
        currencyFlow = BehaviorSubject.create();
    }

    private List<CurrencyViewController> mapCurrencyItems(List<CurrencyItem> items) {
        List<CurrencyViewController> controllers = new ArrayList<>();
        for (CurrencyItem item : items) {
            controllers.add(new CurrencyViewController(item));
        }
        return controllers;
    }

    private void updateCache(List<CurrencyItem> items) {
        currencyItems = items;
    }

    public Flowable<List<CurrencyViewController>> getCurrencyFlow() {
        return currencyFlow.toFlowable(BackpressureStrategy.LATEST).map(this::mapCurrencyItems);
    }

}
