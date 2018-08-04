package com.laban.systemtechnologies.screens.currency.presentation;

import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.DataRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CurrencyDataRepository extends DataRepository {

    Flowable<List<CurrencyItem>> getCurrencyItemFlow();

    Single<List<CurrencyItem>> updateData();

}
