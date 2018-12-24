package com.example.student.mygalleryapp.form;


import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

public class ListViewItem {
    String title;
    Bitmap bitmap;


    //생성자 호출
    public ListViewItem(String title, Bitmap bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }

    //getter setter 호출
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
