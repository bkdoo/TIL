package com.example.student.study_multiplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
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
    TextView tv_mp3;
    MediaPlayer mPlayer;
    boolean bReadPerm = false;
    boolean bWritePerm = false;

    String[] mp3Name = {"/music1.mp3", "/music2.mp3", "/music3.mp3", "/music4.mp3"};
    int index;
    String musicPath;

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

        index = 0;

        btn_playMp3.setOnClickListener(new MyButtonListener());
        btn_stopMp3.setOnClickListener(new MyButtonListener());
        btn_prevMp3.setOnClickListener(new MyButtonListener());
        btn_nextMp3.setOnClickListener(new MyButtonListener());

        mPlayer = new MediaPlayer();

        if (bReadPerm && bWritePerm) {
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                prepareMp3();
            }
        }
    }

    class MyButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_playMp3:
                    playMp3();
                    break;
                case R.id.btn_stopMp3:
                    stopMp3();
                    break;
                case R.id.btn_prevMp3:

                    if (index<=0){
                        index = mp3Name.length-1;
                    } else {
                        index--;
                    }
                    prepareMp3();
                    playMp3();

                    break;
                case R.id.btn_nextMp3:

                    if (index>=mp3Name.length-1){
                        index = 0;
                    } else {
                        index++;
                    }
                    prepareMp3();
                    playMp3();
                    break;
            }
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

    private void prepareMp3(){
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

    private void playMp3(){
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            btn_playMp3.setText("play");
        } else {
            mPlayer.start();
            btn_playMp3.setText("pause");
        }
        tv_mp3.setText(mp3Name[index].substring(1));
    }

    private void stopMp3(){
        mPlayer.stop();
        btn_playMp3.setText("play");
        try {
            mPlayer.prepare();
        } catch (Exception e) {
            Log.d("PlayMp3", "mp3 file error");
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
