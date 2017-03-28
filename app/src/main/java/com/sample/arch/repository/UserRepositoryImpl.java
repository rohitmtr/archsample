package com.sample.arch.repository;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.preference.AppPreference;
import com.sample.arch.retrofit.UserApi;
import com.sample.arch.rx.RxUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class UserRepositoryImpl implements UserRepository {

    private UserApi mUserApi;

    private BehaviorSubject<User> mSelectedUser = BehaviorSubject.create();

    private AppPreference mAppPreference;

    @Inject
    public UserRepositoryImpl(UserApi userApi, AppPreference appPreference) {
        mUserApi = userApi;
        mAppPreference = appPreference;
    }

    @Override
    public Flowable<List<User>> getUsers() {
        return mUserApi.getUsers().toFlowable();
    }


    @Override
    public void setSelected(User user) {
        mAppPreference.setUserId(user.id());
        mSelectedUser.onNext(user);
    }

    @Override
    public Flowable<User> getSelected() {
        return mSelectedUser.toFlowable(BackpressureStrategy.LATEST);
    }
}
