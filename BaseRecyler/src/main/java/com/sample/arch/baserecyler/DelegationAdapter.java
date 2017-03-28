package com.sample.arch.baserecyler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * This is Adapter that will handle delegation logic
 * Provide {@link DataAdapter} and {@link AdapterViewDelegate} objects through constructor
 * This adapter will handle
 */
public class DelegationAdapter extends RecyclerView.Adapter {

    private final AdapterDelegatesManager mDelegatesManager = new AdapterDelegatesManager();
    private DataAdapter mDataAdapterDelegate;

    public DelegationAdapter(DataAdapter dataAdapter, List<AdapterViewDelegate<? extends RecyclerView.ViewHolder>> viewDelegates) {
        for (AdapterViewDelegate<? extends RecyclerView.ViewHolder> delegate : viewDelegates) {
            mDelegatesManager.addDelegate(delegate);
        }
        dataAdapter.setDataObserver(this);
        mDataAdapterDelegate = dataAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDelegatesManager.onBindViewHolder(mDataAdapterDelegate.getItem(position), holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegatesManager.getItemViewType(mDataAdapterDelegate.getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDataAdapterDelegate.getItemCount();
    }

    @Override
    public long getItemId(int position) {
        return mDataAdapterDelegate.getItemId(position);
    }
}
