package com.sample.arch.di;

import com.sample.arch.di.Module.HomeModule;
import com.sample.arch.di.scope.FragmentScope;
import com.sample.arch.home.HomeFragment;

import dagger.Subcomponent;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

@FragmentScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}
