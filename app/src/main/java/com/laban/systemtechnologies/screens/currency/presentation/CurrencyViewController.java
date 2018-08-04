package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.model.entity.CurrencyItem;

public class CurrencyViewController {
    CurrencyItem item;

    public CurrencyViewController(CurrencyItem item) {
        this.item = item;
    }

    public void bind(CurrencyListAdapter.CurrencyHolder currencyHolder) {
        currencyHolder.name.setText(item.getName());
        currencyHolder.rateCharCode.setText(item.getRateCharCode());
        currencyHolder.rate.setText(item.getRate().toString());
        currencyHolder.scaleCharCode.setText(item.getScaleCharCode());
        currencyHolder.scale.setText(String.valueOf(item.getScale()));
    }

}
