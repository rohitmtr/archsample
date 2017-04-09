package com.sample.arch.home.leftmenu;

import com.sample.arch.R;
import com.sample.arch.baserecyler.DelegationAdapter;
import com.sample.arch.data.User;
import com.sample.arch.di.MainActivityComponent;
import com.sample.arch.di.Module.LeftMenuModule;
import com.sample.arch.presenter.home.leftmenu.LeftMenuContract;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class LeftMenuView extends FrameLayout implements LeftMenuContract.View, UserTitleDelegate.UserClickListener {

    @Inject DelegationAdapter mDelegationAdapter;

    @Inject RecyclerView.LayoutManager mLayoutManager;

    @Inject LeftMenuContract.Presenter mPresenter;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @BindView(R.id.userRecyclerView) RecyclerView mRecyclerView;

    public LeftMenuView(Context context) {
        this(context, null);
    }

    public LeftMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setUpDependencyInjection();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDelegationAdapter);
        mPresenter.loadUser();
    }

    protected void setUpDependencyInjection() {
        if (getContext() instanceof MainActivityComponent.ComponentProvider) {
            ((MainActivityComponent.ComponentProvider) getContext()).component()
                    .plus(new LeftMenuModule(this, this))
                    .inject(this);
        }
    }

    @Override
    public void onPostClick(User user) {
        mPresenter.selectUser(user);
    }

    @Override
    public void showLoader() {
        mProgressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoader() {
        mProgressBar.setVisibility(INVISIBLE);
    }
}
