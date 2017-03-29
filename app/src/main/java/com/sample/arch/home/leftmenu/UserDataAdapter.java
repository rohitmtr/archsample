package com.sample.arch.home.leftmenu;

import com.sample.arch.baserecyler.DataAdapter;
import com.sample.arch.data.Post;
import com.sample.arch.data.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class UserDataAdapter extends DataAdapter {

    private List<Object> mItems = new ArrayList<>();

    @Inject
    public UserDataAdapter() {

    }

    public void setPage(List<User> users) {
        mItems.clear();
        mItems.addAll(users);
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