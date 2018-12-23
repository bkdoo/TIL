package com.example.student.study_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_send;
    BroadcastReceiver screenOn;
    BroadcastReceiver screenOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                intent.putExtra("msg", "hello");
                sendBroadcast(intent);
            }
        });

        screenOn = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_state", "screen on");
            }
        };

        screenOff = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_state", "screen off");

            }
        };

        registerReceiver(screenOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(screenOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));



    }
}
