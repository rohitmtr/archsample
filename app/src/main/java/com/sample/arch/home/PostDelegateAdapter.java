package com.sample.arch.home;

import com.sample.arch.baserecyler.AdapterViewDelegate;
import com.sample.arch.baserecyler.DataAdapter;
import com.sample.arch.baserecyler.DelegationAdapter;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class PostDelegateAdapter extends DelegationAdapter {

    @Inject
    public PostDelegateAdapter(PostDataAdapter dataAdapter, PostTitleDelegate delegate) {
        super(dataAdapter, Arrays.<AdapterViewDelegate<?>>asList((delegate)));
    }
}
