package com.example.student.mymovieapp.form;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewItem {
    String title;
    String date;

    //drawable 폴더 내 이미지는 아이디를 가지고 있고 그 아이디를 호출하여 이미지를 불러옴
    int imageId;

    //생성자 호출
    public ListViewItem(String title, String date, int imageId) {
        this.title = title;
        this.date = date;
        this.imageId = imageId;
    }

    //getter setter 호출
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
