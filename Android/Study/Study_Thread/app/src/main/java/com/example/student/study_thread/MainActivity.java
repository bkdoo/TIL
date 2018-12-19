package com.example.student.study_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView_time;
    EditText editText_min, editText_sec;
    Button button_30s, button_1m, button_10m;
    Button button_reset, button_start, button_stop;
    ProgressBar progressBar_main;

    int min, sec;
    Thread myThread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_time = (TextView) findViewById(R.id.textView_time);
        editText_min = (EditText) findViewById(R.id.editText_min);
        editText_sec = (EditText) findViewById(R.id.editText_sec);

        button_30s = (Button) findViewById(R.id.button_30s);
        button_1m = (Button) findViewById(R.id.button_1m);
        button_10m = (Button) findViewById(R.id.button_10m);
        button_reset = (Button) findViewById(R.id.button_reset);

        button_start = (Button) findViewById(R.id.button_start);
        button_stop = (Button) findViewById(R.id.button_stop);

        progressBar_main = (ProgressBar) findViewById(R.id.progressBar_main);


        min = 0;
        sec = 0;

        button_30s.setOnClickListener(new myTimeButtonListener());
        button_1m.setOnClickListener(new myTimeButtonListener());
        button_10m.setOnClickListener(new myTimeButtonListener());
        button_reset.setOnClickListener(new myTimeButtonListener());

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
                setTextView_time(min, sec);
                if (myThread == null) {
                    myThread = new MyThread();
                    myThread.start();
                }

            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myThread != null) {
                    myThread.interrupt();
                    clearTime();
                    myThread = null;
                }
            }
        });

    }


    class myTimeButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_30s:
                    sec += 30;
                    if (sec >= 60) {
                        min += 1;
                        sec -= 60;
                    }
                    break;
                case R.id.button_1m:
                    min += 1;
                    break;
                case R.id.button_10m:
                    min += 10;
                    break;
                case R.id.button_reset:
                    min = 0;
                    sec = 0;
                    break;
            }
            editText_min.setText(String.valueOf(min));
            editText_sec.setText(String.valueOf(sec));
        }
    }

    private void setTime() {
        if (editText_min.getText().toString().equals("")) {
            min = 0;
        } else {
            min = Integer.parseInt(editText_min.getText().toString());
        }
        if (editText_sec.getText().toString().equals("")) {
            sec = 0;
        } else {
            sec = Integer.parseInt(editText_sec.getText().toString());
        }
    }

    private void clearTime() {
        editText_sec.setText("");
        editText_min.setText("");
        setTime();
        textView_time.setText("00:00");
    }

    private void setTextView_time(int min, int sec) {
        String s_min;
        String s_sec;

        if (min < 10) {
            s_min = "0" + editText_min.getText().toString();
        } else {
            s_min = editText_min.getText().toString();
        }
        if (sec < 10) {
            s_sec = "0" + editText_sec.getText().toString();
        } else {
            s_sec = editText_sec.getText().toString();
        }
        textView_time.setText(s_min + ":" + s_sec);
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            try {
                int time = (min * 60) + sec;
                while (!Thread.currentThread().isInterrupted()) {
                    if (time > 0) {
                        Thread.sleep(1000);
                        time--;
                        Message msg = new Message();
                        msg.what = 1;
                        msg.arg1 = time;
                        myHandler.sendMessage(msg);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (msg.arg1 > 0) {
                    min = msg.arg1 / 60;
                    sec = msg.arg1 % 60;
                    setTextView_time(min, sec);
                } else {
                    clearTime();
                }
            }
        }
    };
}
