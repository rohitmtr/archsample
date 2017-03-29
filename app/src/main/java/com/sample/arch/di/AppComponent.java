package com.sample.arch.di;

import com.sample.arch.di.Module.AppModule;
import com.sample.arch.di.Module.MainActivitiyModule;
import com.sample.arch.di.Module.OkHttpModule;
import com.sample.arch.di.Module.RetrofitModule;
import com.sample.arch.di.Module.UserApiModule;
import com.sample.arch.di.Module.UserRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@Singleton
@Component(modules = {AppModule.class, OkHttpModule.class, RetrofitModule.class})
public interface AppComponent {

    MainActivityComponent plus(MainActivitiyModule mainActivitiyModule);

}
