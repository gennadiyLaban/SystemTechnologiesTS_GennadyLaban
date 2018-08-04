package com.laban.systemtechnologies.screens.currency.presentation;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class CurrencyViewModel extends BaseViewModel<CurrencyDataRepository, CurrencyView> {
    private List<CurrencyItem> currencyItems;
    private PublishSubject<List<CurrencyItem>> currencyFlow;
    private Disposable disposable;

    public CurrencyViewModel(CurrencyDataRepository dataRepository) {
        super(dataRepository);
        currencyFlow = PublishSubject.create();
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView(CurrencyView view) {
        super.attachView(view);
        disposable = Flowable.merge(getDataRepository().getCurrencyItemFlow(), view.updateDataFlow().flatMapSingle(o -> getDataRepository().updateData()))
                .doOnNext(this::updateCache).filter(items -> isAttach()).subscribe(currencyFlow::onNext);
        if (currencyItems == null) {
            getDataRepository().updateData().doOnSuccess(this::updateCache).filter(items -> isAttach()).map(this::mapCurrencyItems)
                    .subscribe(controllers -> currencyFlow.onNext(currencyItems));
        } else {
            currencyFlow.onNext(currencyItems);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        currencyFlow.onComplete();
        currencyFlow = PublishSubject.create();
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
