package com.example.student.study_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;

public class ShowPictureActivity extends AppCompatActivity {

    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        iv1= (ImageView) findViewById(R.id.iv1);

        Intent intent = getIntent();
        String filepath = intent.getStringExtra("filepath");

        File file = new File(filepath);
        Bitmap bitmap = BitmapFactory.decodeFile(filepath);
        iv1.setImageBitmap(bitmap);


    }
}
