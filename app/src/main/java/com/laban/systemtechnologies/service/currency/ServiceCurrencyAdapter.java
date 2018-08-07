package com.laban.systemtechnologies.service.currency;

import android.os.RemoteException;

import com.google.gson.Gson;
import com.laban.systemtechnologies.currency.CurrencyRepository;
import com.laban.systemtechnologies.currency.database.CurrencyHolder;
import com.laban.systemtechnologies.errorrs.exceptions.Error;
import com.laban.systemtechnologies.utils.MessageHelper;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;
import laban.ts.systemtechnologies.com.shared_currency_api.CurrencyAidlApi;
import laban.ts.systemtechnologies.com.shared_currency_api.CurrencyApiResponse;

public class ServiceCurrencyAdapter extends CurrencyAidlApi.Stub {
    private MessageHelper messageHelper;
    private CurrencyRepository loadRepository;
    private CurrencyHolder currencyHolder;
    private Gson gson;

    public ServiceCurrencyAdapter(MessageHelper messageHelper, CurrencyRepository loadRepository, CurrencyHolder currencyHolder) {
        this.messageHelper = messageHelper;
        this.loadRepository = loadRepository;
        this.currencyHolder = currencyHolder;
        this.gson = new Gson();
    }

    private String loadCurrency() {
        return loadRepository.getCurrencyCourse()
                .doOnSuccess(currencyHolder::setCurrencyCourses)
                .flatMap(this::mapResponse)
                .onErrorResumeNext(throwable -> mapErrorResponse((Error) throwable, currencyHolder.getCurrencyCourses()))
                .map(gson::toJson)
                .subscribeOn(Schedulers.io()).blockingGet();

    }

    private Single<CurrencyApiResponse> mapErrorResponse(Error error, List<CurrencyItem> items) {
        return buildResponse(items, messageHelper.getErrorMessage(error));
    }

    private Single<CurrencyApiResponse> mapResponse(List<CurrencyItem> items) {
        return buildResponse(items, messageHelper.getSuccessMessage());
    }

    private Single<CurrencyApiResponse> buildResponse(List<CurrencyItem> items, String message) {
        return Single.just(new CurrencyApiResponse(items, message));
    }


    @Override
    public String getCurrencyJson() throws RemoteException {
        return loadCurrency();
    }
}
