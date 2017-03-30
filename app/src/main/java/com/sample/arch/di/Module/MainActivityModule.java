package com.sample.arch.di.Module;

import com.sample.arch.di.scope.ActivityScope;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.domain.user.SetGetUserInteractorImpl;
import com.sample.arch.presenter.home.MainActivityContract;
import com.sample.arch.presenter.home.MainActivityPresenter;
import com.sample.arch.rx.RxBinder;
import com.trello.rxlifecycle2.android.ActivityEvent;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@Module(includes = MainActivityModule.Declarations.class)
public class MainActivityModule {

    private RxBinder<ActivityEvent> mRxBinder;
    private MainActivityContract.View mView;

    public MainActivityModule(RxBinder<ActivityEvent> rxBinder, MainActivityContract.View view) {
        mRxBinder = rxBinder;
        mView = view;
    }

    @Provides
    public MainActivityContract.View provideView() {
        return mView;
    }

    @Provides
    public RxBinder<ActivityEvent> provideRxBinder() {
        return mRxBinder;
    }

    @Module
    public interface Declarations {
        @Binds
        @ActivityScope
        SetGetUserInteractor bindSetGetUser(SetGetUserInteractorImpl setGetUser);

        @Binds
        @ActivityScope
        MainActivityContract.Presenter bindMainActivityPresenter(MainActivityPresenter presenter);
    }
}
