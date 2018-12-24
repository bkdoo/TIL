package com.example.student.mymovieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.mymovieapp.InfoActivity;
import com.example.student.mymovieapp.R;
import com.example.student.mymovieapp.form.ListViewItem;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    int item_layout;
    ArrayList<ListViewItem> list;
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

        if (view == null) {
            view = layoutInflater.inflate(item_layout, viewGroup, false);
        }


        ImageView ivThumb = (ImageView) view.findViewById(R.id.ivThumb);
        ivThumb.setImageResource(list.get(pos).getImageId());
        ivThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        list.get(pos).getTitle() + "를(을) 선택했습니다.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("movie_index", pos);
                intent.putExtra("movie_title", list.get(pos).getTitle());
                intent.putExtra("movie_date", list.get(pos).getDate());
                intent.putExtra("movie_img_id", list.get(pos).getImageId());
                context.startActivity(intent);
            }
        });

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(list.get(pos).getTitle());
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvDate.setText(list.get(pos).getDate());




        return view;
    }
}
