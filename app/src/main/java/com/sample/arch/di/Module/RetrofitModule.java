package com.sample.arch.di.Module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sample.arch.data.AutoValueGsonAdapterFactory;

import android.support.annotation.NonNull;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rohitkumar.yadav on 26/1/17.
 */

@Module
public class RetrofitModule {

    private static final Gson GSON = getCommonGsonBuilder(true).create();
    private String mUrl;

    public RetrofitModule() {
        this("https://jsonplaceholder.typicode.com/");
    }

    public RetrofitModule(String mUrl) {
        this.mUrl = mUrl;
    }

    @Provides
    @NonNull
    public Gson provideGson() {
        return GSON;
    }

    @Provides
    @Named(OkHttpModule.NO_CACHE)
    public Retrofit provideRetrofit(Gson gson, @Named(OkHttpModule.NO_CACHE) OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }


    @Provides
    @Named(OkHttpModule.CACHE)
    public Retrofit provideRetrofitCached(Gson gson, @Named(OkHttpModule.CACHE) OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }


    public static GsonBuilder getCommonGsonBuilder(boolean serializeNulls) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonAdapterFactory.create());
        if (serializeNulls) {
            gsonBuilder.serializeNulls();
        }
        return gsonBuilder;
    }

}