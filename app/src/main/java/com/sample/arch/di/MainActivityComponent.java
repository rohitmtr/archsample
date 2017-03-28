package com.sample.arch.di;

import com.sample.arch.di.Module.HomeModule;
import com.sample.arch.di.Module.MainActvitiyModule;

import dagger.Subcomponent;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

@Subcomponent(modules = MainActvitiyModule.class)
public interface MainActivityComponent {
    HomeComponent plus(HomeModule homeModule);
}
