package com.example.student.study_permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.RECEIVE_SMS);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this,
                    "SMS 수신 권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this,
                    "SMS 수신 권한 없음", Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {
                            Manifest.permission.RECEIVE_SMS
                    }, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,
                            "SMS 권한을 사용자가 승인함",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "SMS 권한을 사용자가 거부함",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
