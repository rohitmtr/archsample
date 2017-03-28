package com.sample.arch.baserecyler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * ViewHolder that is using ButterKnife to initialize Views' objects
 * To use this ViewHolder
 * 1) extends this class
 * 2) declare views and put appropriate @BindView annotation
 * 3) implement {@link ButterKnifeViewHolder#setData(Object)} method to fill views with data
 */
public abstract class ButterKnifeViewHolder<T> extends RecyclerView.ViewHolder {

    public ButterKnifeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected abstract void setData(T item);
}
