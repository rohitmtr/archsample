package com.sample.arch.http;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

abstract class BaseHttpInterceptor implements Interceptor {

    private String mUserAgent;
    private Context mContext;

    public BaseHttpInterceptor(Context context, String userAgent) {
        mUserAgent = userAgent;
        mContext = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response;
        Request.Builder builder = chain.request().newBuilder();

        if (!TextUtils.isEmpty(mUserAgent)) {
            builder.header("User-Agent", mUserAgent);
        }

        response = chain.proceed(builder.build());
        return handleResponse(response);
    }

    private Response handleResponse(Response response) throws IOException {
        int httpStatus = response.code();
        if (httpStatus == HttpURLConnection.HTTP_UNAUTHORIZED ||
                httpStatus == HttpURLConnection.HTTP_FORBIDDEN) {
            //Force log out user
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(HttpUtils.ACTION_LOGOUT));
        }
        return response;
    }
}