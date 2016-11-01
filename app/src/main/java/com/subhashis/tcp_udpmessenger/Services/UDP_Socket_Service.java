package com.subhashis.tcp_udpmessenger.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UDP_Socket_Service extends Service {
    public UDP_Socket_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
