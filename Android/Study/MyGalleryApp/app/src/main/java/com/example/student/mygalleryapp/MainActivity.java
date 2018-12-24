package com.example.student.mygalleryapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.student.mygalleryapp.adapter.ListViewAdapter;
import com.example.student.mygalleryapp.form.ListViewItem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvMedia;
    ListViewAdapter listViewAdapter;
    List<ListViewItem> list;
    boolean bPerm = false;
    File dir;
    File[] fileList;

    final static String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myApp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();

        if (bPerm) {
            dir = new File(FILEPATH);

            fileList = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.toLowerCase().endsWith(".3gp") || s.toLowerCase().endsWith(".jpg");
                }
            });

        }

        lvMedia= (ListView) findViewById(R.id.lvMedia);
        list = new ArrayList<>();
        for (File file : fileList) {
            String title = file.getName();
            String path = file.getAbsolutePath();
            Bitmap bitmap;
            if (title.endsWith(".3gp")){
                bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
            } else {
                bitmap = BitmapFactory.decodeFile(path);
            }
            list.add(new ListViewItem(title, bitmap));
        }

        listViewAdapter = new ListViewAdapter(MainActivity.this,
                R.layout.activity_list_view_item, list);

        lvMedia.setAdapter(listViewAdapter);
        lvMedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }

    private void setPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bPerm = true;
        }


        if(!bPerm) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.READ_EXTERNAL_STORAGE
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
