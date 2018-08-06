package com.laban.systemtechnologies.settings;

import io.reactivex.Flowable;

public interface WorkModeHolder {

    Flowable<WorkMode> getWorkModeFlow();

    WorkMode getWorkMode();

    void setWorkMode(WorkMode workMode);

}
