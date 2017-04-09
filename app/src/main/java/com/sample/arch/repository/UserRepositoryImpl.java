package com.sample.arch.repository;

import com.sample.arch.data.User;
import com.sample.arch.preference.AppPreference;
import com.sample.arch.retrofit.UserApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public Observable<List<User>> getUsers() {
        return mUserApi.getUsers().toObservable();
    }


    @Override
    public void setSelected(User user) {
        mAppPreference.setUserId(user.id());
        mSelectedUser.onNext(user);
    }

    @Override
    public Observable<User> getSelected() {
        return mSelectedUser;
    }
}
