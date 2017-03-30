package com.sample.arch.di;

import com.sample.arch.di.Module.AppModule;
import com.sample.arch.di.Module.MainActivityModule;
import com.sample.arch.di.Module.OkHttpModule;
import com.sample.arch.di.Module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@Singleton
@Component(modules = {AppModule.class, OkHttpModule.class, RetrofitModule.class})
public interface AppComponent {

    MainActivityComponent plus(MainActivityModule mainActivityModule);

}
