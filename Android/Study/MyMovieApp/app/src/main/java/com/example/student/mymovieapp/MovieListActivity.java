package com.example.student.mymovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.mymovieapp.form.ListViewItem;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    ListView lvMovie;

    //자료를 배열로 만들어서 ListView에 전달
    ArrayAdapter adapter;

    final String[] arrMovie= {"블랙펜서", "궁합", "리틀포레스트", "월요일이 사라졌다"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        
        lvMovie= (ListView) findViewById(R.id.lvMovie);


        /**
         * 첫번째 매개변수 : 액티비티 정보 (context 객체)
         * 두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공 : simple_list_item_1)
         * 표시할 데이터들
         */

        ListViewItem item = new ListViewItem("블랙팬서", "2018. 03", R.drawable.black);
        ArrayList<ListViewItem> arrayList = new ArrayList<>();
        arrayList.add(item);
        arrayList.add(new ListViewItem("궁합", "2018. 06", R.drawable.match));
        arrayList.add(new ListViewItem("리틀 포레스트", "2018. 09", R.drawable.little));
        arrayList.add(new ListViewItem("월요일이 사라졌다", "2018. 012", R.drawable.monday));

        //adapter = new ArrayAdapter<String>(MovieListActivity.this, android.R.layout.simple_list_item_1 , arrMovie);

        ListViewAdapter listViewAdapter = new ListViewAdapter(MovieListActivity.this, R.layout.listview_item, arrayList);

        lvMovie.setAdapter(listViewAdapter);

        //리스트뷰에 OnItemclickListener 등록하기
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            }
        });
    }
}
