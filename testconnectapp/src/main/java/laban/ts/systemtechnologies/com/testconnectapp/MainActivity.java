package laban.ts.systemtechnologies.com.testconnectapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import laban.ts.systemtechnologies.com.shared_currency_api.CurrencyAidlApi;

public class MainActivity extends AppCompatActivity {

    CurrencyAidlApi api;

    private ServiceConnection mSecondaryConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // Connecting to a secondary interface is the same as any
            // other interface.
            api = CurrencyAidlApi.Stub.asInterface(service);
            onBind();
            Log.d(getClass().getSimpleName(), "bind");
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(getClass().getSimpleName(), "bind");
            api = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("laban.ts.systemtechnologies.com.systemtechnologiests_gennadylaban", "com.laban.systemtechnologies.service.CurrencyApiService"));
        bindService(intent, mSecondaryConnection, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mSecondaryConnection);
    }

    private void onBind() {
        try {
            Log.d(getClass().getSimpleName(), api.getCurrencyJson());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
