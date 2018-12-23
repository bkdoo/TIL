package com.example.student.mymovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtId, edtPwd1, edtPwd2;
    TextView tvResult;
    Button btnJoin;
    String id, pwd, pwd2;
    boolean idOk, pwdOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = (EditText) findViewById(R.id.edt_loginId);
        edtPwd1 = (EditText) findViewById(R.id.edt_loginPwd);
        edtPwd2 = (EditText) findViewById(R.id.edtPwd2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        id = edtId.getText().toString();
        pwd = edtPwd1.getText().toString();
        pwd2 = edtPwd2.getText().toString();

        idOk = false;
        pwdOk = false;

        isRightId(id);
        isRightPwd(pwd, pwd2);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setVisibility(View.INVISIBLE);

                isFull(id, pwd, pwd2);
                isSuccessful();
            }
        });
    }

    private void isSuccessful() {
        if (idOk && pwdOk)
            Toast.makeText(this, "환영합니다!", Toast.LENGTH_SHORT).show();
    }

    private void isFull(String id, String pwd, String pwd2) {
        if (id.isEmpty() || pwd.isEmpty() || pwd2.isEmpty()){
            tvResult.setVisibility(View.VISIBLE);
            tvResult.setText("정보를 확인해 주세요.");
        }
    }

    private void isRightPwd(String pwd, String pwd2) {
        if (pwd.isEmpty() || pwd2.isEmpty()) {
            tvResult.setVisibility(View.INVISIBLE);
        } else {
            if (!pwd.equals(pwd2)){
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("비밀번호가 일치하지 않습니다.");
                pwdOk = false;
            } else {
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("비밀번호가 일치합니다.");
                pwdOk = true;

            }
        }
    }



    private void isRightId(String id) {
        if (id.isEmpty()) {
            tvResult.setVisibility(View.INVISIBLE);
        } else {

            if (id.length() <5 || id.length() >12){
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("비정상적인 아이디 입니다. (5~12자)");
                idOk = false;
            } else {
                tvResult.setVisibility(View.VISIBLE);
                tvResult.setText("정상적인 아이디 입니다. ");
                idOk = true;
            }
        }
    }
}
