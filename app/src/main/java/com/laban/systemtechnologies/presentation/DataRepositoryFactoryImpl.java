package com.laban.systemtechnologies.presentation;

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
import io.reactivex.subjects.BehaviorSubject;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {


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
                    private BehaviorSubject<List<CurrencyItem>> currencyFlow = BehaviorSubject.create();

                    @Override
                    public Flowable<List<CurrencyItem>> getCurrencyItemFlow() {
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
                        currencyFlow.onNext(items);
                        return currencyFlow.toFlowable(BackpressureStrategy.BUFFER);
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
