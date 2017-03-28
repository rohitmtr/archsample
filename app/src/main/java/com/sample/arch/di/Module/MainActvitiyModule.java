package com.sample.arch.di.Module;

import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.domain.user.SetGetUserInteractorImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@Module
public abstract class MainActvitiyModule {

    @Binds
    public abstract SetGetUserInteractor bindSetGetUser(SetGetUserInteractorImpl setGetUser);

}
