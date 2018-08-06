package com.laban.systemtechnologies.screens.settings;

import android.os.Bundle;
import android.widget.RadioButton;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsView;
import com.laban.systemtechnologies.screens.settings.presentation.SettingsViewModel;
import com.laban.systemtechnologies.settings.WorkMode;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class SettingsActivity extends BaseActivity<SettingsViewModel, SettingsView> implements SettingsView {
    private PublishSubject<WorkMode> workModeFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        workModeFlow = PublishSubject.create();
        ((RadioButton) findViewById(R.id.normal_work_mode)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                workModeFlow.onNext(WorkMode.NORMAL_WORK);
            }
        });
        ((RadioButton) findViewById(R.id.content_work_mode)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                workModeFlow.onNext(WorkMode.CONTENT_EXCEPTION);
            }
        });
        ((RadioButton) findViewById(R.id.not_found_work_mode)).setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                workModeFlow.onNext(WorkMode.NOT_FOUND_EXCEPTION);
            }
        });
    }

    @Override
    protected Screen getScreen() {
        return Screen.SETTINGS;
    }

    @Override
    public Flowable<WorkMode> getWorkModeFlow() {
        return workModeFlow.toFlowable(BackpressureStrategy.LATEST);
    }

    @Override
    public void setWorkMode(WorkMode workMode) {
        RadioButton radioButton;
        switch (workMode) {
            case NORMAL_WORK:
                radioButton = findViewById(R.id.normal_work_mode);
                break;
            case NOT_FOUND_EXCEPTION:
                radioButton = findViewById(R.id.not_found_work_mode);
                break;
            case CONTENT_EXCEPTION:
                radioButton = findViewById(R.id.content_work_mode);
                break;
            default:
                radioButton = findViewById(R.id.normal_work_mode);
        }
        radioButton.setChecked(true);
    }


}
