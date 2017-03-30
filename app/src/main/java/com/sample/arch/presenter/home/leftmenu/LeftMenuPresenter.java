package com.sample.arch.presenter.home.leftmenu;

import com.sample.arch.data.User;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.domain.user.UserInteractor;
import com.sample.arch.home.leftmenu.UserDataAdapter;
import com.sample.arch.rx.RxBinder;
import com.trello.rxlifecycle2.android.ActivityEvent;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by rohitkumar.yadav on 29/3/17.
 */

public class LeftMenuPresenter implements LeftMenuContract.Presenter {

    private LeftMenuContract.View mView;
    private RxBinder<ActivityEvent> mRxBinder;
    private SetGetUserInteractor mSetGetUserInteractor;
    private UserDataAdapter mUserDataAdapter;
    private UserInteractor mUserInteractor;


    @Inject
    public LeftMenuPresenter(@NonNull LeftMenuContract.View view,
                             @NonNull RxBinder<ActivityEvent> rxBinder,
                             @NonNull SetGetUserInteractor setGetUserInteractor,
                             @NonNull UserDataAdapter userDataAdapter,
                             @NonNull UserInteractor userInteractor) {

        mView = view;
        mRxBinder = rxBinder;
        mSetGetUserInteractor = setGetUserInteractor;
        mUserDataAdapter = userDataAdapter;
        mUserInteractor = userInteractor;
    }

    @Override
    public void loadUser() {
        mView.showLoader();
        mUserInteractor.getUsers()
                .compose(mRxBinder.<List<User>>asyncCallWithinLifecycle())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        mUserDataAdapter.setPage(users);
                        mView.hideLoader();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideLoader();
                        Log.e("LeftMenuPresePresenter:", "", throwable);
                    }
                });
    }

    @Override
    public void selectUser(User user) {
        mSetGetUserInteractor.setSelectedUser(user);
    }
}
