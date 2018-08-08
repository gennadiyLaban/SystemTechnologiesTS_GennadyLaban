package com.laban.systemtechnologies.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.laban.systemtechnologies.service.currency.ServiceCurrencyAipiHolder;

import laban.ts.systemtechnologies.com.shared_currency_api.CurrencyAidlApi;

public class CurrencyApiService extends Service {
    private int FOREGROUND_ID = 12345;
    private CurrencyAidlApi.Stub mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        attachBinder();
    }

    private void attachBinder() {
        mBinder = ((ServiceCurrencyAipiHolder) getApplication()).getCurrencyAidlApi();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(FOREGROUND_ID, buildForegroundNotification("Hahahaha"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Notification buildForegroundNotification(String filename) {

        Notification.Builder b = new Notification.Builder(this);

        b.setOngoing(true)
                .setContentTitle("notify")
                .setContentText(filename)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setTicker("ticker");

        return (b.build());
    }
}
