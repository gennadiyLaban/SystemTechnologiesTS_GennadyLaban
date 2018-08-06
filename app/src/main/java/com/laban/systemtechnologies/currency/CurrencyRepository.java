package com.laban.systemtechnologies.currency;

import java.util.List;

import io.reactivex.Single;
import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;

public interface CurrencyRepository {

    Single<List<CurrencyItem>> getCurrencyCourse();

}
