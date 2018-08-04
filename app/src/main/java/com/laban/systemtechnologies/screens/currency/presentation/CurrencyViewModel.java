package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class CurrencyViewModel extends BaseViewModel<CurrencyDataRepository, CurrencyView> {
    private Disposable currencyDispossible;

    public CurrencyViewModel(CurrencyDataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    public void attachView(CurrencyView view) {
        super.attachView(view);
        currencyDispossible = getDataRepository().getCurrencyItemFlow().map(this::mapCurrencyItems)
                .subscribe(currencyItems -> getView().setCurrencyList(currencyItems));
    }

    @Override
    public void detachView() {
        super.detachView();
        if (currencyDispossible != null && !currencyDispossible.isDisposed()) {
            currencyDispossible.dispose();
        }
    }

    private List<CurrencyViewController> mapCurrencyItems(List<CurrencyItem> items) {
        List<CurrencyViewController> controllers = new ArrayList<>();
        for (CurrencyItem item : items) {
            controllers.add(new CurrencyViewController(item));
        }
        return controllers;
    }

}
