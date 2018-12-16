package com.example.student.mymovieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.mymovieapp.form.ListViewItem;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewItem> list;
    Context context;
    int  item_layout;
    LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int item_layout, ArrayList<ListViewItem> list) {
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
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final int pos2 = pos+1;

        if (view == null) {
            view = layoutInflater.inflate(item_layout, viewGroup, false);
        }



        if (pos%2==0) {
            ImageView ivThumb = (ImageView) view.findViewById(R.id.ivThumb);
            ivThumb.setImageResource(list.get(pos).getImageId());

            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvTitle.setText(list.get(pos).getTitle());
            TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvDate.setText(list.get(pos).getDate());

            if (pos2<list.size()) {

                ImageView ivThumb2 = (ImageView) view.findViewById(R.id.ivThumb2);
                ivThumb2.setImageResource(list.get(pos2).getImageId());

                TextView tvTitle2 = (TextView) view.findViewById(R.id.tvTitle2);
                tvTitle2.setText(list.get(pos2).getTitle());
                TextView tvDate2 = (TextView) view.findViewById(R.id.tvDate2);
                tvDate2.setText(list.get(pos2).getDate());
            }


        } else {
            view = null;
        }



        return view;
    }
}
