package com.laban.systemtechnologies.currency;

import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.List;

import io.reactivex.Single;

public interface CurrencyRepository {

    Single<List<CurrencyItem>> getCurrencyCourse();

}
