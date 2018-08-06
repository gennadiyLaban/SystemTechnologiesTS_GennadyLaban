package com.laban.systemtechnologies.settings;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class WorkModeManager implements WorkModeHolder {
    private static final String SHARED_PREFERENCE_KEY = "KEY_WORK_MODE_PREFERENCE";
    private static final String WORK_MODE_KEY = "KEY_WORK_MODE";

    private SharedPreferences preferences;
    private PublishSubject<WorkMode> workModeFlow;

    public WorkModeManager(SharedPreferences preferences) {
        this.preferences = preferences;
        this.workModeFlow = PublishSubject.create();
    }

    @Override
    public Flowable<WorkMode> getWorkModeFlow() {
        return workModeFlow.toFlowable(BackpressureStrategy.LATEST);
    }

    @Override
    public WorkMode getWorkMode() {
        return WorkMode.fromCode(preferences.getInt(WORK_MODE_KEY, WorkMode.NORMAL_WORK.getCode()));
    }

    @Override
    public void setWorkMode(WorkMode workMode) {
        preferences.edit().putInt(WORK_MODE_KEY, workMode.getCode()).apply();
        workModeFlow.onNext(workMode);
    }


    public static WorkModeManager newInstance(Context context) {
        return new WorkModeManager(context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE));
    }

}
