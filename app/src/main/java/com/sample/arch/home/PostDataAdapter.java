package com.sample.arch.home;

import com.sample.arch.baserecyler.DataAdapter;
import com.sample.arch.data.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class PostDataAdapter extends DataAdapter {

    private List<Object> mItems = new ArrayList<>();

    @Inject
    public PostDataAdapter() {

    }

    public void setPage(List<Post> posts) {
        mItems.clear();
        mItems.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}