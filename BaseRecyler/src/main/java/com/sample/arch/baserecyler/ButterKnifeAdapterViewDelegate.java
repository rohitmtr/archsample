package com.sample.arch.baserecyler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;


/**
 * Use this delegate to connect your {@link ButterKnifeViewHolder} children classes
 * @param <T> data type
 * @param <VH> view holder type
 */
public abstract class ButterKnifeAdapterViewDelegate<T, VH extends ButterKnifeViewHolder<T>> extends AdapterViewDelegate<VH> {

    @Override
    public void onBindViewHolder(@NonNull Object item, @NonNull RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        ((VH) holder).setData((T) item);
    }
}
