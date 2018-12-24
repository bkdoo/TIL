package com.example.student.study_video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {

    MediaRecorder mediaRecorder;
    Camera camera;
    boolean bPerm;
    boolean isRecording = false;
    TextureView textureV_video;
    Button btn_shot;
    int result;
    SurfaceTexture surface;

    List<Camera.Size> supportedPreviewSizes;
    Camera.Size previewSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureV_video= (TextureView) findViewById(R.id.textureV_video);
        textureV_video.setSurfaceTextureListener(this);

        btn_shot= (Button) findViewById(R.id.btn_shot);
        btn_shot.setOnClickListener(new ShotBtnListener());

        setPermission();

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        camera = Camera.open();

        Camera.Parameters parameters = camera.getParameters();
        supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes != null) {
            previewSize = CameraUtil.getOptimalPreviewSize(supportedPreviewSizes, width, height);
            parameters.setPreviewSize(previewSize.width, previewSize.height);
        }

        int result = CameraUtil.setCameraDisplayOrientation(this,0);
        this.result = result;
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setRotation(result);

        camera.setParameters(parameters);
        camera.setDisplayOrientation(result);

        try {
            camera.setPreviewTexture(surface);
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();
        this.surface = surface;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        camera.stopPreview();
        camera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    private class ShotBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (camera != null) {
                if (isRecording) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;
                    btn_shot.setText("Recoding");
                } else {
                    try {
                        File dir = new File(Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/myApp");
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File file = File.createTempFile("VIDEO-", ".3gp", dir);

                        mediaRecorder = new MediaRecorder();

                        camera.unlock();
                        mediaRecorder.setCamera(camera);
                        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

                        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_1080P));
                        mediaRecorder.setOutputFile(file.getAbsolutePath());

                        mediaRecorder.setOrientationHint(result);
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaRecorder.start();
                    isRecording = true;
                    btn_shot.setText("STOP");
                }
            }
        }
    }

    private void setPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) ==
                PackageManager.PERMISSION_GRANTED
                ) {
            bPerm = true;
        }


        if(!bPerm) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO
                    }, 200);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bPerm = true;
            }
        }
    }
}
