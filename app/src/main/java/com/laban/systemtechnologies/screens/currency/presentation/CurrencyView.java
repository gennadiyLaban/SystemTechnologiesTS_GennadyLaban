package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.screens.BaseView;

import io.reactivex.Flowable;

public interface CurrencyView extends BaseView {

    Flowable<Object> updateDataFlow();

}
