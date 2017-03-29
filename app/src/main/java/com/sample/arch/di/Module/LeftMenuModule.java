package com.sample.arch.di.Module;

import com.sample.arch.baserecyler.DelegationAdapter;
import com.sample.arch.di.scope.ViewScope;
import com.sample.arch.domain.user.UserInteractor;
import com.sample.arch.domain.user.UserInteractorImpl;
import com.sample.arch.home.leftmenu.UserDataAdapter;
import com.sample.arch.home.leftmenu.UserDelegateAdapter;
import com.sample.arch.home.leftmenu.UserTitleDelegate;
import com.sample.arch.presenter.home.leftmenu.LeftMenuContract;
import com.sample.arch.presenter.home.leftmenu.LeftMenuPresenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

@Module(includes = {LeftMenuModule.Declarations.class})
public class LeftMenuModule {

    private LeftMenuContract.View mView;
    private  UserTitleDelegate.UserClickListener mUserClickListener;

    public LeftMenuModule(LeftMenuContract.View view, UserTitleDelegate.UserClickListener userClickListener) {
        mView = view;
        mUserClickListener = userClickListener;
    }

    @Module
    public interface Declarations {

        @Binds
        UserInteractor bindPostRepository(UserInteractorImpl userInteractor);

        @Binds
        LeftMenuContract.Presenter bindPresenter(LeftMenuPresenter presenter);

        @Binds
        DelegationAdapter bindPostDelegateAdapter(UserDelegateAdapter userDelegateAdapter);

    }

    @Provides
    public LeftMenuContract.View provideView() {
        return mView;
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @ViewScope
    public UserTitleDelegate providePostTitleDelegate() {
        return new UserTitleDelegate(mUserClickListener);
    }

    @Provides
    @ViewScope
    public UserDataAdapter providePostDataAdapter() {
        return new UserDataAdapter();
    }
}
