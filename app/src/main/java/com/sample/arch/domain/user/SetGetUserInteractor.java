package com.sample.arch.domain.user;

import com.sample.arch.data.User;
import com.sample.arch.domain.UseCase;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public interface SetGetUserInteractor extends UseCase<User> {

    Observable<User> getSelectedUser();

    void setSelectedUser(User user);
}
