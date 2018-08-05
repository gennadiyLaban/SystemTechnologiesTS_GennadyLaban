package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.screens.BaseView;
import com.laban.systemtechnologies.screens.currency.presentation.recyclerview.MoveAction;

import io.reactivex.Flowable;

public interface CurrencyView extends BaseView {

    Flowable<Object> updateDataFlow();

    Flowable<MoveAction> moveItemsFlow();

}
