package com.example.student.study_multiplayer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {


    MediaPlayer mPlayer;


    String[] mp3Name = {"/music1.mp3", "/music2.mp3", "/music3.mp3", "/music4.mp3"};
    int index;
    String musicPath;


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("log_myService", "onReceive");
            index = 2;


            String state = Environment.getExternalStorageState();


            if (state.equals(Environment.MEDIA_MOUNTED)) {
                prepareMp3();
            }
            String mode = intent.getStringExtra("mode");
            if (mode != null) {
                if (mode.equals("play")) {
                    playMp3();
                } else if (mode.equals("stop")) {
                    stopMp3();
                } else if (mode.equals("prev")) {
                    if (index <= 0) {
                        index = 3;
                    } else {
                        index--;
                    }
                    prepareMp3();
                    playMp3();
                } else if (mode.equals("next")) {
                    if (index >= 3) {
                        index = 0;
                    } else {
                        index++;
                    }
                    prepareMp3();
                    playMp3();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        //서비스는 객체를 여러개 만들 수 없음, 싱글톤

        super.onCreate();
        Log.d("log_myService", "onCreate");
        // mPlayer를 onCreate에서 만듬, 여기서 만들지 않으면 play시 객체가 계속 생성되어 돌림노래됨
        mPlayer = new MediaPlayer();
        //액티비티와 통신을 위한 리시버를 등록
        registerReceiver(receiver, new IntentFilter("com.example.student.study_multiplayer"));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("log_myService", "onStartCommand");



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("log_myService", "onDestroy");
        //서비스가 종료되면 리시버를 해체
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("log_myService", "onBind()");
        return null;
    }

    private void prepareMp3() {
        try {
            mPlayer.reset();
            musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + mp3Name[index];
            mPlayer.setDataSource(musicPath);
            mPlayer.prepare();
            Log.d("PlayMp3", "mp3 file ");
        } catch (Exception e) {
            Log.d("PlayMp3", "mp3 file error");
        }
    }


    private void playMp3() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
    }

    private void stopMp3() {
        mPlayer.stop();
        try {
            mPlayer.prepare();
        } catch (Exception e) {
            Log.d("PlayMp3", "mp3 file error");
        }
    }
}
