package com.laban.systemtechnologies.data;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.currency.CurrencyRepository;
import com.laban.systemtechnologies.settings.WorkMode;
import com.laban.systemtechnologies.settings.WorkModeHolder;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;

public class DataManager implements WorkModeHolder {
    private PublishSubject<List<CurrencyItem>> currencyItemFlow;
    private CurrencyRepository currencyRepository;
    private WorkModeHolder workModeHolder;

    public DataManager(CurrencyRepository repository, WorkModeHolder workModeHolder) {
        this.currencyRepository = repository;
        this.workModeHolder = workModeHolder;
        currencyItemFlow = PublishSubject.create();
    }

    @SuppressLint("CheckResult")
    public void updateCurrencyCourses() {
        currencyRepository.getCurrencyCourse().subscribe(currencyItemFlow::onNext);
    }

    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
        return currencyItemFlow.toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<WorkMode> getWorkModeFlow() {
        return workModeHolder.getWorkModeFlow();
    }

    @Override
    public WorkMode getWorkMode() {
        return workModeHolder.getWorkMode();
    }

    @Override
    public void setWorkMode(WorkMode workMode) {
        workModeHolder.setWorkMode(workMode);
    }
}
