package com.laban.systemtechnologies.screens.settings.presentation;

import com.laban.systemtechnologies.screens.BaseView;
import com.laban.systemtechnologies.settings.WorkMode;

import io.reactivex.Flowable;

public interface SettingsView extends BaseView {

    Flowable<WorkMode> getWorkModeFlow();

    void setWorkMode(WorkMode workMode);

}
