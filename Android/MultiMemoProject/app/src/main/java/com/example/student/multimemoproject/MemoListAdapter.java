package com.example.student.multimemoproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2018-11-26.
 */

public class MemoListAdapter extends BaseAdapter{
    private Context mContext;
    private List<MemoListItem> mItems = new ArrayList<>();

    public MemoListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void clear(){
        mItems.clear();
    }

    public void setListitems(List<MemoListItem> mItems) {
        this.mItems = mItems;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }



    public void addItem(MemoListItem aItem) {
        mItems.add(aItem);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        MemoListItemView itemView;
        if (convertView == null){
            itemView = new MemoListItemView(mContext);
        } else {
            itemView = (MemoListItemView) convertView;
        }

        itemView.setContents(0, ((String) mItems.get(position).getData(0)));
        itemView.setContents(1, ((String) mItems.get(position).getData(1)));
        itemView.setContents(2, ((String) mItems.get(position).getData(3)));
        itemView.setContents(3, ((String) mItems.get(position).getData(5)));

        itemView.setMediaState(mItems.get(position).getData(7),
                mItems.get(position).getData(9));

        return itemView;
    }
}
