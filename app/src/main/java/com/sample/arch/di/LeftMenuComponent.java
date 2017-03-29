package com.sample.arch.di;

import com.sample.arch.di.Module.LeftMenuModule;
import com.sample.arch.di.scope.ViewScope;
import com.sample.arch.home.leftmenu.LeftMenuView;

import dagger.Subcomponent;

/**
 * Created by rohitkumar.yadav on 29/3/17.
 */

@ViewScope
@Subcomponent(modules = LeftMenuModule.class)
public interface LeftMenuComponent {

    void inject(LeftMenuView leftMenuView);

}
