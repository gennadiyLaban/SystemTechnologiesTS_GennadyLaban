package com.laban.systemtechnologies.currency;

import com.laban.systemtechnologies.currency.database.CurrencyHolder;
import com.laban.systemtechnologies.model.CurrencyRepository;
import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

public class CurrencyPresenter implements CurrencyRepository {
    private CurrencyRepository httpRepository;
    private CurrencyHolder currencyHolder;
    private PublishSubject<Throwable> errorFlow;

    public CurrencyPresenter(CurrencyRepository httpRepository, CurrencyHolder currencyHolder) {
        this.httpRepository = httpRepository;
        this.currencyHolder = currencyHolder;
        errorFlow = PublishSubject.create();
    }

    @Override
    public Single<List<CurrencyItem>> getCurrencyCourse() {
        return httpRepository.getCurrencyCourse()
                .doOnSuccess(currencyHolder::setCurrencyCourses)
                .onErrorResumeNext(throwable -> Single.just(currencyHolder.getCurrencyCourses()))
                .doOnError(throwable -> errorFlow.onNext(throwable));
    }

    public Flowable<Throwable> getErrorFlow() {
        return errorFlow.toFlowable(BackpressureStrategy.BUFFER);
    }

}
