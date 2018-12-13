package com.example.student.study_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnCustomer, btnSale, btnProduct;
    public static final int REQUEST_CODE_CUSTOMER = 101;
    public static final int REQUEST_CODE_SALE = 102;
    public static final int REQUEST_CODE_PRODUCT = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("메인 메뉴");

        setComponent();

        MyButtonListener myButtonListner = new MyButtonListener();

        btnCustomer.setOnClickListener(myButtonListner);
        btnSale.setOnClickListener(myButtonListner);
        btnProduct.setOnClickListener(myButtonListner);
    }

    private void setComponent() {
        btnCustomer= (Button) findViewById(R.id.btnCustomer);
        btnSale= (Button) findViewById(R.id.btnSale);
        btnProduct= (Button) findViewById(R.id.btnProduct);
    }


    private class MyButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.btnCustomer:
                    intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.putExtra("name", "고객 관리");
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case R.id.btnSale:
                    intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.putExtra("name", "매출 관리");
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                case R.id.btnProduct:
                    intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.putExtra("name", "상품 관리");
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }
        }
    }
}
