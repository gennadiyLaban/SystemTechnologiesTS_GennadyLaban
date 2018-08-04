package com.laban.systemtechnologies.screens.currency;

import android.os.Bundle;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyView;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyViewModel;

public class CurrencyListActivity extends BaseActivity<CurrencyViewModel, CurrencyView> implements CurrencyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
    }

    @Override
    protected Screen getScreen() {
        return Screen.CURRENCY_LIST;
    }
}
