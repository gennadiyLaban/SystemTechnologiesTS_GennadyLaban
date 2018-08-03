package com.laban.systemtechnologies.screens.currency;

import android.os.Bundle;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;

public class CurrencyListActivity extends BaseActivity<CurrencyViewModel, CurrencyView> {

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
