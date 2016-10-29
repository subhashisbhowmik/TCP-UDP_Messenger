package com.subhashis.tcp_udp_messenger.services;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UDP_Socket_Service extends Service {

    private final IBinder mBinder = new LocalBinder();
    int logCount = 0;
    int threadCount = 0;
    Map<Activity, Boolean> activityMap = new HashMap<>();
    Map<Integer, Boolean> portMap = new HashMap<>();

    List<Thread> listeningThreadList = new ArrayList<>();

    public UDP_Socket_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void startLogging() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
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

    public boolean listenTCP(int port) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        if (portMap.containsKey(port)) {
            if (portMap.get(port)) portMap.remove(port);
            else return false;
        }
        try {
            serverSocket = new ServerSocket(port);
            portMap.put(port, false);
        } catch (IOException e) {
            Log.w("ERROR", e.toString());
            return false;
        }
        while (!portMap.get(port)) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                Log.w("ERROR", e.toString());
            }
            final Socket finalSocket=socket;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    startConnection(finalSocket);
                }
            });
            listeningThreadList.add(t);
        }
        portMap.remove(port);
        return true;
    }

    public boolean stopTCPPort(int port) {
        if (portMap.containsKey(port)) {
            portMap.put(port, true);
            return true;
        } else return false;
    }

    void startConnection(Socket socket) {
        //TODO: Use class
    }

    public boolean attachActivity(Context c) {
        return activityMap.put((Activity) c, true);
    }

    public boolean detachActivity(Context c) {
        return activityMap.remove((Activity) c);
    }

    public boolean stopIfDone() {
        if (threadCount > 0) return false;
        stopSelf();
        return true;
    }

    public class LocalBinder extends Binder {
        public UDP_Socket_Service getService() {
            return UDP_Socket_Service.this;
        }
    }

}
