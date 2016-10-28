package com.subhashis.tcp_udp_messenger.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.subhashis.tcp_udp_messenger.R;
import com.subhashis.tcp_udp_messenger.services.UDP_Socket_Service;

public class MainActivity extends AppCompatActivity {

    UDP_Socket_Service mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startService(new Intent(this, UDP_Socket_Service.class));
        Intent intent = new Intent(this, UDP_Socket_Service.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mService==null);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mService.startLogging();
                    }
                });
            }
        }).start();
        //mService.startLogging();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mService.stopIfDone();
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            UDP_Socket_Service.LocalBinder binder = (UDP_Socket_Service.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

}
