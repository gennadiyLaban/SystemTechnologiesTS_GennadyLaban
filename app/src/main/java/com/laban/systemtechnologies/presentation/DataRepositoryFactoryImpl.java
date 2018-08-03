package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.DataRepository;
import com.laban.systemtechnologies.screens.Screen;

public class DataRepositoryFactoryImpl implements DataRepositoryFactory {


    @Override
    public <T extends DataRepository> T createRepository(Screen screen) {
        return null;
    }
}
