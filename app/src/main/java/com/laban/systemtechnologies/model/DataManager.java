package com.laban.systemtechnologies.model;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class DataManager {
    private PublishSubject<List<CurrencyItem>> currencyItemFlow;
    private CurrencyRepository currencyRepository;

    public DataManager(CurrencyRepository repository) {
        this.currencyRepository = repository;
        currencyItemFlow = PublishSubject.create();
    }

    @SuppressLint("CheckResult")
    public void updateCurrencyCourses() {
        currencyRepository.getCurrencyCourse().subscribe(currencyItemFlow::onNext);
    }

    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
        return currencyItemFlow.toFlowable(BackpressureStrategy.BUFFER);
    }

}
