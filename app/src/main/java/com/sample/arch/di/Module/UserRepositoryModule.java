package com.sample.arch.di.Module;

import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.PostRepositoryImpl;
import com.sample.arch.repository.UserRepository;
import com.sample.arch.repository.UserRepositoryImpl;
import com.sample.arch.retrofit.UserApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@Module
public abstract class UserRepositoryModule {

    //use @binds to create the object by dagger
    @Binds
    public abstract UserRepository bindRepository(UserRepositoryImpl userRepository);
}
