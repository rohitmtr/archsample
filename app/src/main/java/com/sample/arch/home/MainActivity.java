package com.sample.arch.home;

import com.sample.arch.App;
import com.sample.arch.R;
import com.sample.arch.di.MainActivityComponent;
import com.sample.arch.rx.RxActivity;
import com.sample.arch.utils.FragmentUtils;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RxActivity {

   @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    private MainActivityComponent mSubcomponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            FragmentUtils.replaceAddToStack(getSupportFragmentManager(), R.id.content_fragment, HomeFragment.newInstance());
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void setUpDependencyInjection() {
        super.setUpDependencyInjection();
        mSubcomponent = App.get(getApplication()).getAppComponent().plus();
    }

    public MainActivityComponent getSubcomponent() {
        return mSubcomponent;
    }
}
