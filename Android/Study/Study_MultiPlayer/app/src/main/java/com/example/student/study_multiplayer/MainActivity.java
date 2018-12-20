package com.example.student.study_multiplayer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_playMp3, btn_stopMp3;
    Button btn_prevMp3, btn_nextMp3;
    TextView tv_mp3, textView_position, textView_duration;

    boolean bReadPerm = false;
    boolean bWritePerm = false;

    int position;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();
        btn_playMp3 = (Button) findViewById(R.id.btn_playMp3);
        btn_stopMp3 = (Button) findViewById(R.id.btn_stopMp3);
        btn_prevMp3 = (Button) findViewById(R.id.btn_prevMp3);
        btn_nextMp3 = (Button) findViewById(R.id.btn_nextMp3);
        tv_mp3 = (TextView) findViewById(R.id.tv_mp3);
        textView_position= (TextView) findViewById(R.id.textView_position);
        textView_duration= (TextView) findViewById(R.id.textView_duration);



        btn_playMp3.setOnClickListener(new MyButtonListener());
        btn_stopMp3.setOnClickListener(new MyButtonListener());
        btn_prevMp3.setOnClickListener(new MyButtonListener());
        btn_nextMp3.setOnClickListener(new MyButtonListener());

        Intent intent_to_service = new Intent(getApplicationContext(), MyService.class);
        startService(intent_to_service);

        registerReceiver(receiver_from_service, new IntentFilter("com.example.student.study_multiplayer"));

    }



    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver_from_service);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent_to_service = new Intent(getApplicationContext(), MyService.class);
        stopService(intent_to_service);
    }

    BroadcastReceiver receiver_from_service = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String playMode = intent.getStringExtra("isPlaying");
            if (playMode != null){
                btn_playMp3.setText(playMode);

            }

            int duration = intent.getIntExtra("duration", 0);
            textView_duration.setText(convertTime(duration));
            position = intent.getIntExtra("position", 0);
            textView_position.setText(convertTime(position));
        }
    };


    private String convertTime(int time) {
        String result = "";

        int sec = (time / 1000) % 60;
        int min = (time / (1000 * 60)) % 60;
        int hour = (time / (1000 * 60 * 60)) % 24;

        if(hour < 10) {
            result += "0"+hour+":";
        } else {
            result += hour+":";
        }

        if(min < 10) {
            result += "0"+min+":";
        } else {
            result += hour+":";
        }

        if(sec < 10) {
            result += "0"+sec;
        } else {
            result += sec;
        }
        return result;
    }


    class MyButtonListener implements View.OnClickListener {

        Intent intent;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_playMp3:
                    intent = new Intent("com.example.student.study_multiplayer");
                    intent.putExtra("mode", "play");
                    break;
                case R.id.btn_stopMp3:
                    intent = new Intent("com.example.student.study_multiplayer");
                    intent.putExtra("mode", "stop");
                    break;
                case R.id.btn_prevMp3:
                    intent = new Intent("com.example.student.study_multiplayer");
                    intent.putExtra("mode", "prev");
                    break;
                case R.id.btn_nextMp3:
                    intent = new Intent("com.example.student.study_multiplayer");
                    intent.putExtra("mode", "next");
                    break;


            }
            sendBroadcast(intent);
        }
    }

    class myTask extends AsyncTask<Void, Void, Void> {

        Intent intent = new Intent("com.example.student.study_multiplayer");

        @Override
        protected Void doInBackground(Void... voids) {
            while (isCancelled() == false) {
                SystemClock.sleep(1000);
                sendBroadcast(intent);

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            intent.putExtra("time", "running_time");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }

    private void setPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            bReadPerm = true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            bWritePerm = true;
        }

        if (!bReadPerm && !bWritePerm) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bWritePerm = true;
            }
        }
    }


}
