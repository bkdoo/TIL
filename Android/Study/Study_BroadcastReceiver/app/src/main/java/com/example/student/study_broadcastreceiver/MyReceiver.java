package com.example.student.study_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg") + "\nI am BroadcastReceiver";
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
