package com.sample.arch.baserecyler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


class AdapterDelegatesManager {

    private SparseArrayCompat<AdapterViewDelegate<? extends RecyclerView.ViewHolder>> delegates = new SparseArrayCompat<>();

    AdapterDelegatesManager addDelegate(@NonNull AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate) {
        int viewType = delegates.size();
        while (delegates.get(viewType) != null) {
            viewType++;
        }
        return addDelegate(viewType, delegate);
    }

    private AdapterDelegatesManager addDelegate(int viewType, @NonNull AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate) {
        if (delegates.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An AdapterDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered AdapterDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public int getItemViewType(@NonNull Object item) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item)) {
                return delegates.keyAt(i);
            }
        }

        throw new NullPointerException(
                "No AdapterDelegate added that matches " + item + " in data source");
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate = getDelegateForViewType(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
        return delegate.onCreateViewHolder(parent);
    }

    public <VH extends RecyclerView.ViewHolder> void onBindViewHolder(@NonNull Object item, @NonNull VH viewHolder) {
        AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate = getDelegateForViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = "
                    + item
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onBindViewHolder(item, viewHolder);
    }

    @Nullable
    protected AdapterViewDelegate<? extends RecyclerView.ViewHolder> getDelegateForViewType(int viewType) {
        return delegates.get(viewType);
    }

}
