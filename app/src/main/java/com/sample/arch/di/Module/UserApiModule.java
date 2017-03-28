package com.sample.arch.di.Module;

import com.sample.arch.retrofit.UserApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

@Module
public class UserApiModule {

    @Provides
    public static UserApi provideUserApi(@Named(OkHttpModule.CACHE) Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
