package com.laban.systemtechnologies.currency.http;

import android.util.Log;

import com.laban.systemtechnologies.exceptions.NetworkException;
import com.laban.systemtechnologies.model.CurrencyRepository;
import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class CurrencyLoader implements CurrencyRepository {
    private static final String BASE_URL_NB_RB = "http://www.nbrb.by/";
    private static final String SCALE_CURRENCY = "BYN";

    private CurrencyApi currencyApi;

    public CurrencyLoader() {
        Retrofit currency = new Retrofit.Builder()
                .baseUrl(BASE_URL_NB_RB)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        currencyApi = currency.create(CurrencyApi.class);
    }

    public Single<List<CurrencyItem>> getCurrencyCourse() {
        return currencyApi.getCurrencyList().map(result -> {
            Response<CurrencyResponse> response = result.response();
            if (result.isError() || !response.isSuccessful()) {
                if (result.isError()) {
                    Log.e(getClass().getSimpleName(), result.error().toString());
                } else {
                    Log.d(getClass().getSimpleName(), response.message() + " " + response.code());
                }
                throw new NetworkException();
            }
            return response.body().getCourses();
        }).flatMap(this::convertCurrencyCorses);
    }

    private Single<List<CurrencyItem>> convertCurrencyCorses(List<CurrencyCourse> courses) {
        return Flowable.fromIterable(courses).map(this::converCurrencyCorse).toList();
    }

    private CurrencyItem converCurrencyCorse(CurrencyCourse course) {
        CurrencyItem item = new CurrencyItem();
        item.setScaleCharCode(SCALE_CURRENCY);
        item.setScale(course.getScale());
        item.setRateCharCode(course.getCharCode());
        item.setRate(course.getRate());
        item.setName(course.getName());
        return item;
    }

}
