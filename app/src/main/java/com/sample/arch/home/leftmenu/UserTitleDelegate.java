package com.sample.arch.home.leftmenu;

import com.sample.arch.R;
import com.sample.arch.baserecyler.ButterKnifeAdapterViewDelegate;
import com.sample.arch.baserecyler.ButterKnifeViewHolder;
import com.sample.arch.data.User;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;


public class UserTitleDelegate extends ButterKnifeAdapterViewDelegate<User, UserTitleDelegate.TitleViewHolder> {

    public interface UserClickListener {
        void onPostClick(User post);
    }

    private UserClickListener mUserClickListener;

    @Inject
    public UserTitleDelegate(UserClickListener clickListener) {
        mUserClickListener = clickListener;
    }

    @Override public boolean isForViewType(Object item) {
        return (item instanceof User);
    }

    @NonNull
    @Override
    public UserTitleDelegate.TitleViewHolder onCreateViewHolder(ViewGroup parent) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item, parent, false);
        return new UserTitleDelegate.TitleViewHolder(v);
    }

    final class TitleViewHolder extends ButterKnifeViewHolder<User> {

        @BindView(R.id.textView) TextView mName;


        public TitleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void setData(final User user) {
            mName.append(user.name());
            mName.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mUserClickListener.onPostClick(user);
                }
            });
        }
    }
}