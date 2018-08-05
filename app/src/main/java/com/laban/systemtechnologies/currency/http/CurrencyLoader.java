package com.laban.systemtechnologies.currency.http;

import com.laban.systemtechnologies.currency.CurrencyRepository;
import com.laban.systemtechnologies.errorrs.exceptions.DefaultError;
import com.laban.systemtechnologies.errorrs.exceptions.NetworkConnectionException;
import com.laban.systemtechnologies.errorrs.exceptions.NetworkException;
import com.laban.systemtechnologies.errorrs.exceptions.ServerError;
import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
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
            checkResult(result);
            Response<CurrencyResponse> response = result.response();
            return response.body().getCourses();
        }).flatMap(this::convertCurrencyCorses);
    }

    private void checkResult(Result result) throws NetworkException {
        if (result.isError()) {
            try {
                throw result.error();
            } catch (IOException e) {
                throw new NetworkConnectionException();
            } catch (Throwable throwable) {
                throw new DefaultError(throwable.getMessage());
            }
        }
        Response response = result.response();
        if (!response.isSuccessful()) {
            throw new ServerError(response.code());
        }
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
