package com.sample.arch.baserecyler;

import android.support.v7.widget.RecyclerView;


public abstract class DataAdapter {

    private RecyclerView.Adapter mAdapter;

    public abstract Object getItem(int position);

    public abstract int getItemCount();

    public abstract long getItemId(int position);

    public final void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public final void notifyItemChanged(int position) {
        mAdapter.notifyItemChanged(position);
    }

    final void setDataObserver(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

}
