package com.sample.arch.home;

import com.sample.arch.App;
import com.sample.arch.R;
import com.sample.arch.data.User;
import com.sample.arch.di.MainActivityComponent;
import com.sample.arch.di.Module.MainActivityModule;
import com.sample.arch.presenter.home.MainActivityContract;
import com.sample.arch.rx.RxActivity;
import com.sample.arch.utils.FragmentUtils;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RxActivity implements MainActivityComponent.ComponentProvider, MainActivityContract.View {

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    private MainActivityComponent mSubcomponent;

    /// have to inject to listen the user change
    @Inject MainActivityContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            FragmentUtils.replace(getSupportFragmentManager(), R.id.content_fragment, HomeFragment.newInstance(),false);
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onUserChange(User user) {
        setTitle(user.name());
        closeDrawer();
    }

    @Override
    public void onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed();
        }
    }

    private boolean closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    protected void setUpDependencyInjection() {
        super.setUpDependencyInjection();
        mSubcomponent = App.get(getApplication())
                .getAppComponent()
                .plus(new MainActivityModule(this, this));
        mSubcomponent.inject(this);
    }

    @Override
    public MainActivityComponent component() {
        return mSubcomponent;
    }
}
