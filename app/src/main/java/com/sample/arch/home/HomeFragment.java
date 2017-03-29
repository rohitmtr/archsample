package com.sample.arch.home;

import com.sample.arch.R;
import com.sample.arch.baserecyler.DelegationAdapter;
import com.sample.arch.data.Post;
import com.sample.arch.di.MainActivityComponent;
import com.sample.arch.di.Module.HomeModule;
import com.sample.arch.presenter.home.HomeContract;
import com.sample.arch.rx.RxFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class HomeFragment extends RxFragment implements HomeContract.HomeView , PostTitleDelegate.PostClickListener {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @BindView(R.id.commentRecyclerView) RecyclerView mRecyclerView;

    @Inject HomeContract.Presenter mPresenter;

    @Inject DelegationAdapter mDelegationAdapter;

    @Inject RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        setUpDependencyInjection();


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDelegationAdapter);

        return view;
    }


    @Override
    protected void setUpDependencyInjection() {
        if (getActivity() instanceof MainActivityComponent.ComponentProvider) {
            ((MainActivityComponent.ComponentProvider) getActivity()).component()
                    .plus(new HomeModule(this, this, this))
                    .inject(this);
        }
    }

    @Override
    public void showLoader() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPostClick(Post post) {
        Toast.makeText(getActivity(),post.body(),Toast.LENGTH_LONG).show();
    }
}
