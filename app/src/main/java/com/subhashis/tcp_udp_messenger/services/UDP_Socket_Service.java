package com.subhashis.tcp_udp_messenger.services;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class UDP_Socket_Service extends Service {

    private final IBinder mBinder = new LocalBinder();
    int logCount=0;
    int threadCount=0;
    Map<Activity,Boolean> activityMap =new HashMap<Activity, Boolean>();

    public UDP_Socket_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void startLogging(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Log.d("INFO", String.valueOf(++logCount));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public boolean attachActivity(Context c){
        return activityMap.put((Activity)c,true);
    }

    public boolean detachActivity(Context c){
        return activityMap.remove((Activity)c);
    }

    public boolean stopIfDone(){
        if(threadCount>0) return false;
        stopSelf();
        return true;
    }

    public class LocalBinder extends Binder {
        public UDP_Socket_Service getService(){
            return UDP_Socket_Service.this;
        }
    }

}
