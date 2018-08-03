package com.laban.systemtechnologies.screens.main;

import android.os.Bundle;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel, MainView> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
