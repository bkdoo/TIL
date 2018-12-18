package com.example.student.study_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            Intent intent_to_activity = new Intent("com.example.student.study_service");
            if (mode != null) {
                if (mode.equals("send1")) {
                    Log.d("myService_receiver","send1");
                    intent_to_activity.putExtra("res","response from send1");
                } else if (mode.equals("send2")) {
                    Log.d("myService_receiver","send2");
                    intent_to_activity.putExtra("res","response from send2");
                }
                sendBroadcast(intent_to_activity);
            }
        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("log_MyService", "onCreate()");
        registerReceiver(receiver,new IntentFilter("com.example.student.study_service"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("log_MyService", "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("log_MyService", "onDestroy()");
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("log_MyService", "onBind()");
        return null;
    }
}
