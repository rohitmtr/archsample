package com.sample.arch.di.Module;

import com.sample.arch.di.scope.ActivityScope;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.domain.user.SetGetUserInteractorImpl;
import com.sample.arch.rx.RxBinder;
import com.trello.rxlifecycle2.android.ActivityEvent;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@Module(includes = MainActivitiyModule.Declarations.class)
public class MainActivitiyModule {

    private RxBinder<ActivityEvent> mRxBinder;

    public MainActivitiyModule(RxBinder<ActivityEvent> rxBinder) {
        mRxBinder = rxBinder;
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
    }
}
