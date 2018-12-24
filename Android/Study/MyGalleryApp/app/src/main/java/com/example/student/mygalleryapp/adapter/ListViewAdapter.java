package com.example.student.mygalleryapp.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.mygalleryapp.R;
import com.example.student.mygalleryapp.form.ListViewItem;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;


/**
 * Created by student on 2018-12-24.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    int item_layout;
    List<ListViewItem> list;
    LayoutInflater layoutInflater;



    public ListViewAdapter(Context context, int item_layout, List<ListViewItem> list) {
        this.context = context;
        this.item_layout = item_layout;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        final int pos = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(item_layout, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        String title = list.get(pos).getTitle();
        tvTitle.setText(title.substring(0, title.length()-4));

        ImageView ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
        ivThumb.setImageBitmap(list.get(pos).getBitmap());


        return convertView;
    }
}
