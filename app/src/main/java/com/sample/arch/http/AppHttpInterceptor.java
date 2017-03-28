package com.sample.arch.http;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class AppHttpInterceptor extends BaseHttpInterceptor {

    @Inject
    public AppHttpInterceptor(Context context, String userAgent) {
        super(context, userAgent);
    }
}
