package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.screens.Screen;

public interface DataRepositoryFactory {

    <T extends DataRepository> T createRepository(Screen screen);

}
