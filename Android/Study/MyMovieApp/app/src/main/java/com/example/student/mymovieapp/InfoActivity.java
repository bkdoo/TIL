package com.example.student.mymovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    int movie_index;
    String movie_title, movie_date;
    int movie_img_id;

    final int MOVIE_INFO_REQUEST_CODE = 101;

    TextView textview_movie_title, textview_movie_date;
    RatingBar ratingbar_star_point;
    Button btn_go_book;

    //* Scroll view code
    LinearLayout linearLayout_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();        // 전달받은 인텐트를 수신

        // 컴포넌트의 객체를 생성
        textview_movie_title = (TextView) findViewById(R.id.textview_movie_title);
        textview_movie_date = (TextView) findViewById(R.id.textview_movie_date);
        ratingbar_star_point = (RatingBar) findViewById(R.id.ratingbar_star_point);
        btn_go_book = (Button) findViewById(R.id.btn_go_book);

        // 스크롤 뷰 안에 있는 linear layout
        linearLayout_info = (LinearLayout) findViewById(R.id.linearLayout_info);


        if (intent != null && movie_index != -1) {
            movie_index = intent.getIntExtra("movie_index", -1);    // 인텐트에서 전달된 데이터 읽음
            movie_title = intent.getStringExtra("movie_title");
            movie_date = intent.getStringExtra("movie_date");
            movie_img_id = intent.getIntExtra("movie_img_id", -1);

            textview_movie_title.setText(movie_title);
            textview_movie_date.setText("개봉일 : " + movie_date);

            // 스크롤 뷰안에 있는 리니어 레이아웃이 자바 코드로 이미지 뷰를 추가
            ImageView temp = new ImageView(InfoActivity.this);
            temp.setImageResource(movie_img_id);
            temp.setLayoutParams(new LinearLayout.LayoutParams(500, 600));
            temp.setScaleType(ImageView.ScaleType.FIT_XY);
            linearLayout_info.addView(temp);



        } else {
            Toast.makeText(InfoActivity.this,
                    "동작중에 오류가 발생하였습니다.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }



}
