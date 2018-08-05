package com.laban.systemtechnologies.model;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.currency.http.CurrencyLoader;
import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class DataManager {
    private PublishSubject<List<CurrencyItem>> currencyItemFlow;
    private CurrencyRepository currencyRepository;

    public DataManager(CurrencyLoader repository) {
        this.currencyRepository = repository;
    }

    @SuppressLint("CheckResult")
    public void updateCurrencyCourses() {
        currencyRepository.getCurrencyCourse().subscribe(currencyItemFlow::onNext);
    }

    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
        return currencyItemFlow.toFlowable(BackpressureStrategy.LATEST);
    }

}
