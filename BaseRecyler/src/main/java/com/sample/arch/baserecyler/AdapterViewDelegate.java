package com.sample.arch.baserecyler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public abstract class AdapterViewDelegate<VH extends RecyclerView.ViewHolder> {

    public abstract boolean isForViewType(Object item);

    @NonNull
    public abstract VH onCreateViewHolder(ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull Object item, @NonNull RecyclerView.ViewHolder holder);
}
