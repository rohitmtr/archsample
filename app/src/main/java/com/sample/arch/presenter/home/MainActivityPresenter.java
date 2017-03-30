package com.sample.arch.presenter.home;

import com.sample.arch.data.User;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.rx.RxBinder;
import com.trello.rxlifecycle2.android.ActivityEvent;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {


    private SetGetUserInteractor mSetGetUserInteractor;
    private MainActivityContract.View mView;
    private RxBinder<ActivityEvent> mRxBinder;

    @Inject
    public MainActivityPresenter(@NonNull SetGetUserInteractor setGetUserInteractor,
                                 @NonNull MainActivityContract.View view,
                                 @NonNull RxBinder<ActivityEvent> rxBinder) {

        mSetGetUserInteractor = setGetUserInteractor;
        mView = view;
        mRxBinder = rxBinder;
        startObservingSelectedUser();
    }


    private void startObservingSelectedUser() {
        mSetGetUserInteractor.getSelectedUser()
                .compose(mRxBinder.<User>asyncCallWithinLifecycle())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mView.onUserChange(user);
                    }
                });
    }


}
