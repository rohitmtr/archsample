package com.sample.arch.domain.user;

import com.sample.arch.data.User;
import com.sample.arch.preference.AppPreference;
import com.sample.arch.repository.UserRepository;
import com.sample.arch.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class SetGetUserInteractorImpl implements SetGetUserInteractor {

    private UserRepository mUserRepository;
    private AppPreference mAppPreference;

    @Inject
    public SetGetUserInteractorImpl(UserRepository userRepository, AppPreference appPreference) {
        mUserRepository = userRepository;
        mAppPreference = appPreference;
        mUserRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                final int userId = mAppPreference.getUserId();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user.id() == userId) {
                        setSelectedUser(user);
                        break;
                    }
                }
            }
        }, RxUtils.errorDefault());
    }

    @Override
    public Flowable<User> getSelectedUser() {
        return mUserRepository.getSelected();
    }

    @Override
    public void setSelectedUser(User user) {
        mUserRepository.setSelected(user);
    }
}
