package com.sample.arch.repository;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public interface UserRepository {

    Flowable<List<User>> getUsers();

    void setSelected(User user);

    Flowable<User> getSelected();
}
