package com.example.student.mymovieapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edt_loginId, edt_loginPwd;
    Button btn_login;
    SharedPreferences sharedPref;


    final static String[] STR = {"파일 읽기", "파일 쓰기", "인터넷", "진동"};
    final static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.VIBRATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int[] permissionCheck = {
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE),
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.INTERNET),
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.VIBRATE)
        };


        for (int i = 0; i < permissionCheck.length; i++) {

            if (permissionCheck[i] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(),
                        STR[i] + " 권한 있음", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        STR[i] + " 권한 없음", Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{
                                PERMISSIONS[i]
                        }, i);
            }
        }


        edt_loginId = (EditText) findViewById(R.id.edt_loginId);
        edt_loginPwd = (EditText) findViewById(R.id.edt_loginPwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        sharedPref = getSharedPreferences("login_info", Context.MODE_PRIVATE);

        if (!sharedPref.getString("autoLogin", "").equals("")) {
            Intent intent = new Intent(LoginActivity.this,
                    MovieListActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edt_loginId.getText().toString();
                String pwd = edt_loginPwd.getText().toString();
                if (authUser(id, pwd)) {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("autoLogin", id);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,
                            MovieListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean authUser(String id, String pw) {

        if (id.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "ID를 입력해주세요.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if (pw.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "비밀번호를 입력해주세요.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        // 비밀번호와 패스워드를 검증한다. 현재는 임시 코드
        if (id.equals("user") && pw.equals("1234")) {
            // 아이디와 비밀번호가 맞는 경우
            return true;
        } else {
            // 아이디와 비밀번호가 다른 경우
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), STR[0] + "권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), STR[0] + "권한을 사용자가 거부함", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), STR[1] + "권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), STR[1] + "권한을 사용자가 거부함", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), STR[2] + "권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), STR[2] + "권한을 사용자가 거부함", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), STR[3] + "권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), STR[3] + "권한을 사용자가 거부함", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

