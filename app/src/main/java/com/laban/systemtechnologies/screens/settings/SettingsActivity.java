package com.laban.systemtechnologies.screens.settings;

import android.os.Bundle;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;

public class SettingsActivity extends BaseActivity<SettingsViewModel, SettingsView> implements SettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}