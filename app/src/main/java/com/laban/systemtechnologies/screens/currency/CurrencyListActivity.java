package com.laban.systemtechnologies.screens.currency;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyListAdapter;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyView;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyViewController;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyViewModel;

import java.util.ArrayList;
import java.util.List;

public class CurrencyListActivity extends BaseActivity<CurrencyViewModel, CurrencyView> implements CurrencyView {
    private RecyclerView recyclerView;
    private CurrencyListAdapter currencyListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        initCurrencyList();

    }

    private void initCurrencyList() {
        recyclerView = findViewById(R.id.currency_course_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        currencyListAdapter = new CurrencyListAdapter(new ArrayList<>());
        recyclerView.setAdapter(currencyListAdapter);
    }

    @Override
    protected Screen getScreen() {
        return Screen.CURRENCY_LIST;
    }

    @Override
    public void setCurrencyList(List<CurrencyViewController> currencyList) {
        currencyListAdapter.replaceData(currencyList);
    }
}
