package com.laban.systemtechnologies.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.CurrencyListActivity;
import com.laban.systemtechnologies.screens.main.presentation.MainView;
import com.laban.systemtechnologies.screens.main.presentation.MainViewModel;
import com.laban.systemtechnologies.screens.settings.SettingsActivity;
import com.laban.systemtechnologies.service.CurrencyApiService;

public class MainActivity extends BaseActivity<MainViewModel, MainView> implements MainView {
    private View settingsBtn;
    private View currencyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsBtn = findViewById(R.id.settings);
        currencyBtn = findViewById(R.id.currency);
    }

    @Override
    protected Screen getScreen() {
        return Screen.MAIN;
    }

    @Override
    public void openCurrencyScreen() {
        startActivity(new Intent(this, CurrencyListActivity.class));
    }

    @Override
    public void openSettingsScreen() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    protected void attachPresenter() {
        super.attachPresenter();
        settingsBtn.setOnClickListener(v -> getViewModel().onPressSettingsBtn());
        currencyBtn.setOnClickListener(v -> getViewModel().onPressCurrencyBtn());
        findViewById(R.id.service).setOnClickListener(v -> startService(new Intent(this, CurrencyApiService.class)));
    }
}
