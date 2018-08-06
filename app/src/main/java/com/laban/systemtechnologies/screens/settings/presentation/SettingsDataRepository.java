package com.laban.systemtechnologies.screens.settings.presentation;

import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.settings.WorkMode;


public interface SettingsDataRepository extends DataRepository {

    WorkMode getWorkMode();

    void setWorkMode(WorkMode workMode);

}
