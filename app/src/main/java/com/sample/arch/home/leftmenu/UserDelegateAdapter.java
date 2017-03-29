package com.sample.arch.home.leftmenu;

import com.sample.arch.baserecyler.AdapterViewDelegate;
import com.sample.arch.baserecyler.DelegationAdapter;
import com.sample.arch.home.PostDataAdapter;
import com.sample.arch.home.PostTitleDelegate;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class UserDelegateAdapter extends DelegationAdapter {

    @Inject
    public UserDelegateAdapter(UserDataAdapter dataAdapter, UserTitleDelegate delegate) {
        super(dataAdapter, Arrays.<AdapterViewDelegate<?>>asList((delegate)));
    }
}
