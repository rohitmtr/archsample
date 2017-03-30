package com.sample.arch.di;

import com.sample.arch.di.Module.HomeModule;
import com.sample.arch.di.Module.LeftMenuModule;
import com.sample.arch.di.Module.MainActivityModule;
import com.sample.arch.di.Module.UserApiModule;
import com.sample.arch.di.Module.UserRepositoryModule;
import com.sample.arch.di.scope.ActivityScope;
import com.sample.arch.home.MainActivity;

import dagger.Subcomponent;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class, UserRepositoryModule.class, UserApiModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    HomeComponent plus(HomeModule homeModule);

    LeftMenuComponent plus(LeftMenuModule menuModule);

    interface ComponentProvider {
        MainActivityComponent component();
    }

}
