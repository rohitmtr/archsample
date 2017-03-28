package com.sample.arch.baserecyler;

import java.util.ArrayList;
import java.util.List;


public class ListDataAdapter extends DataAdapter {

    private final List<Object> mItems = new ArrayList<>();

    public void setItems(List items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setItem(Object item) {
        int size = mItems.size();
        mItems.add(item);
        notifyItemChanged(size);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void setItem(Object item, int position) {
        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @Override
    public final Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public final int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
