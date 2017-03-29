package com.sample.arch.di;

import com.sample.arch.di.Module.HomeModule;
import com.sample.arch.di.Module.MainActvitiyModule;
import com.sample.arch.di.Module.UserApiModule;
import com.sample.arch.di.Module.UserRepositoryModule;
import com.sample.arch.di.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@ActivityScope
@Subcomponent(modules = {MainActvitiyModule.class, UserRepositoryModule.class, UserApiModule.class})
public interface MainActivityComponent {
    HomeComponent plus(HomeModule homeModule);
}
