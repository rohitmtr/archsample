package com.sample.arch.home;

import com.sample.arch.App;
import com.sample.arch.R;
import com.sample.arch.data.User;
import com.sample.arch.di.MainActivityComponent;
import com.sample.arch.di.Module.MainActivitiyModule;
import com.sample.arch.domain.user.SetGetUserInteractor;
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
import io.reactivex.functions.Consumer;

public class MainActivity extends RxActivity implements MainActivityComponent.ComponentProvider {

   @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    private MainActivityComponent mSubcomponent;

    //Experiment
    @Inject
    SetGetUserInteractor mSetGetUserInteractor;

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

        mSetGetUserInteractor
                .getSelectedUser()
                .compose(this.<User>asyncCallWithinLifecycle()).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                setTitle(user.name());
                closeDrawer();
            }
        });
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
                .plus(new MainActivitiyModule(this));
        mSubcomponent.inject(this);
    }

    @Override
    public MainActivityComponent component() {
        return mSubcomponent;
    }
}
