package com.laban.systemtechnologies.screens.settings;

import android.os.Bundle;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsView;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsViewModel;

public class SettingsActivity extends BaseActivity<SettingsViewModel, SettingsView> implements SettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected Screen getScreen() {
        return Screen.SETTINGS;
    }
}
