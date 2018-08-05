package com.laban.systemtechnologies.presentation;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.currency.http.CurrencyLoader;
import com.laban.systemtechnologies.model.entity.CurrencyItem;
import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyDataRepository;
import com.laban.systemtechnologies.screens.main.presentation.MainDataRepository;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsDataRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {
    private CurrencyLoader currencyLoader;

    public DataRepositoryFactoryImpl(CurrencyLoader currencyLoader) {
        this.currencyLoader = currencyLoader;
    }

    @Override
    public <T extends DataRepository> T createRepository(Screen screen) {
        DataRepository dataRepository = null;
        switch (screen) {
            case MAIN:
                dataRepository = new MainDataRepository() {
                };
                break;
            case CURRENCY_LIST:
                dataRepository = new CurrencyDataRepository() {
                    private PublishSubject<List<CurrencyItem>> currencyFlow = PublishSubject.create();


                    @Override
                    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
                        return currencyFlow.toFlowable(BackpressureStrategy.BUFFER);
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void updateData() {
                        currencyLoader.getCurrencyCourse()
                                .subscribe((items, throwable) -> {
                                    if (throwable == null) {
                                        currencyFlow.onNext(items);
                                    } else {
                                        currencyFlow.onError(throwable);
                                    }
                                });
                    }

                    private List<CurrencyItem> initList() {
                        List<CurrencyItem> items = new ArrayList<>();
                        CurrencyItem item = new CurrencyItem();
                        item.setName("Российский рубль");
                        item.setRate(new BigDecimal("62.3"));
                        item.setRateCharCode("RUB");
                        item.setScale(1);
                        item.setScaleCharCode("BYN");
                        items.add(item);

                        item = new CurrencyItem();
                        item.setName("Не Российский рубль");
                        item.setRate(new BigDecimal("100.3"));
                        item.setRateCharCode("RUBss");
                        item.setScale(1);
                        item.setScaleCharCode("BYN");
                        items.add(item);
                        return items;
                    }

                };
                break;
            case SETTINGS:
                dataRepository = new SettingsDataRepository() {
                };
                break;
        }
        return (T) dataRepository;
    }
}
