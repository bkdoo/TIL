package com.example.student.multimemoproject;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.student.multimemoproject.common.TitleBitmapButton;

/**
 * Created by student on 2018-11-27.
 */

public class PhotoCaptureActivity extends AppCompatActivity {
    public static final String TAG = "PhotoCaptureActivity";

    CameraSurfaceView mCameraView;

    FrameLayout mFrameLayout;

    /**
     * 버튼을 두 번 이상 누를 때 문제 해결
     */
    boolean processing = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상태바와 타이틀 설정
        final Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.photo_capture_activity);

       // mCameraView = new CameraSurfaceView(getApplicationContext());
        mFrameLayout = (FrameLayout) findViewById(R.id.frame);
        //mFrameLayout.addView(mCameraView);

        setCaptureBtn();

    }

    public void setCaptureBtn() {
        TitleBitmapButton takeBtn = (TitleBitmapButton) findViewById(R.id.capture_takeBtn);
        takeBtn.setBackgroundBitmap(R.drawable.btn_camera_capture_normal, R.drawable.btn_camera_capture_click);
        takeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!processing) {
                    processing = true;
                    //mCameraView.capture(new CameraPictureCallback());
                }
            }
        });
    }

    /**
     * 키 이벤트 처리 (카메라 찍기 버튼)
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_CAMERA) {
            //mCameraView.capture(new CameraPictureCallback());

            return true;
        } else if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();

            return true;
        }

        return false;
    }

    //class CameraPictureCallback implements Camera.PictureCallback{
    //}
}
