package com.sample.arch.domain.user;

import com.sample.arch.data.User;
import com.sample.arch.domain.UseCase;
import com.sample.arch.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class UserInteractorImpl implements UserInteractor {

    private UserRepository mUserRepository;

    @Inject
    public UserInteractorImpl(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public Flowable<List<User>> getUsers() {
        return mUserRepository.getUsers();
    }
}
