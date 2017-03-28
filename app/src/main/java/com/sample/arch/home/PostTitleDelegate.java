package com.sample.arch.home;

import com.sample.arch.R;
import com.sample.arch.baserecyler.ButterKnifeAdapterViewDelegate;
import com.sample.arch.baserecyler.ButterKnifeViewHolder;
import com.sample.arch.data.Post;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;


public class PostTitleDelegate extends ButterKnifeAdapterViewDelegate<Post, PostTitleDelegate.TitleViewHolder> {

    public interface PostClickListener {
        void onPostClick(Post post);
    }

    private PostClickListener mPostClickListener;

    @Inject
    public PostTitleDelegate(PostClickListener clickListener) {
        mPostClickListener = clickListener;
    }

    @Override public boolean isForViewType(Object item) {
        return (item instanceof Post);
    }

    @NonNull
    @Override
    public PostTitleDelegate.TitleViewHolder onCreateViewHolder(ViewGroup parent) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_list_item, parent, false);
        return new PostTitleDelegate.TitleViewHolder(v);
    }

    final class TitleViewHolder extends ButterKnifeViewHolder<Post> {

        @BindView(R.id.textView) TextView mName;


        public TitleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void setData(final Post post) {
            mName.setText(post.title());
            mName.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    mPostClickListener.onPostClick(post);
                }
            });
        }
    }
}