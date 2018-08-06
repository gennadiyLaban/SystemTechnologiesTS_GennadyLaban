package com.laban.systemtechnologies.settings;

public class WorkModeHolderLocator {
    private static WorkModeHolder workModeHolder;

    public static void setWorkModeHolder(WorkModeHolder workModeHolder) {
        if (WorkModeHolderLocator.workModeHolder == null) {
            WorkModeHolderLocator.workModeHolder = workModeHolder;
        }
    }

    public static WorkModeHolder getWorkModeHolder() {
        return workModeHolder;
    }

}
