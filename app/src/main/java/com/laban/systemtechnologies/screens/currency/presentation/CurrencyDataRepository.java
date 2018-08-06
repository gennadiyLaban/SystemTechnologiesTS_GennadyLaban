package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.screens.DataRepository;

import java.util.List;

import io.reactivex.Flowable;
import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;

public interface CurrencyDataRepository extends DataRepository {

    Flowable<List<CurrencyItem>> getCurrencyItemFlow();

    void updateData();

}
