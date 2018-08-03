package com.laban.systemtechnologies.presentation;

import com.laban.systemtechnologies.screens.BaseViewModel;
import com.laban.systemtechnologies.screens.Screen;

public interface ViewModelFactory {

    <T extends BaseViewModel> T onCreateScreen(Screen screen);

    void onDestroyScreen(Screen screen);

}
