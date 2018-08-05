package com.laban.systemtechnologies.screens.currency.presentation;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.currency.presentation.recyclerview.MoveAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;

public class CurrencyViewModel extends BaseViewModel<CurrencyDataRepository, CurrencyView> {
    private List<CurrencyItem> currencyItems;
    private BehaviorSubject<List<CurrencyItem>> currencyFlow;
    private CompositeDisposable disposables = new CompositeDisposable();

    public CurrencyViewModel(CurrencyDataRepository dataRepository) {
        super(dataRepository);
        currencyFlow = BehaviorSubject.create();
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView(CurrencyView view) {
        super.attachView(view);
        disposables.add(getView().updateDataFlow().subscribe(o -> getDataRepository().updateData()));
        disposables.add(getDataRepository().getCurrencyItemFlow().doOnNext(this::updateCache).filter(items -> isAttach()).subscribe(currencyFlow::onNext));
        disposables.add(view.moveItemsFlow().subscribe(this::moveItem));

        if (currencyItems == null) {
            getDataRepository().updateData();
        } else {
            currencyFlow.onNext(currencyItems);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        disposables.dispose();
        disposables.clear();
        currencyFlow.onComplete();
        currencyFlow = BehaviorSubject.create();
    }

    private void moveItem(MoveAction moveAction) {
        if (moveAction.fromPosition < moveAction.toPosition) {
            for (int i = moveAction.fromPosition; i < moveAction.toPosition; i++) {
                Collections.swap(currencyItems, i, i + 1);
            }
        } else {
            for (int i = moveAction.fromPosition; i > moveAction.toPosition; i--) {
                Collections.swap(currencyItems, i, i - 1);
            }
        }
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
