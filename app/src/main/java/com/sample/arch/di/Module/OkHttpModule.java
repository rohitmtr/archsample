package com.sample.arch.di.Module;

import com.sample.arch.http.AppHttpInterceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

@Module(includes = {OkHttpModule.OkHttpDeclarations.class})
public class OkHttpModule {

    public static final String CACHE = "cache";
    public static final String NO_CACHE = "noCache";
    private static final String CACHE_DIR = "default";
    public static final long CONNECTION_TIMEOUT = 60 * 1000;

    private String mDir;
    private List<Interceptor> mInterceptors = new ArrayList<>(0);
    private String mUserAgent;

    public OkHttpModule(String userAgent) {
        this(CACHE_DIR, userAgent);
    }

    public OkHttpModule(String dir,String userAgent) {
        this.mDir = dir;
        mUserAgent = userAgent;
    }

    public OkHttpModule addInterceptor(Interceptor interceptor) {
        mInterceptors.add(interceptor);
        return this;
    }

    @Provides
    Cache provideCache(Context context) {
        long SIZE_OF_CACHE = 10 * 1024 * 1024;
        return new Cache(new File(context.getCacheDir(), mDir), SIZE_OF_CACHE);
    }

    @Named(CACHE)
    @Provides
    OkHttpClient provideOkHttpCachedClient(Cache cache, Interceptor appIntercaptor) {
        return getCommonOkHttpBuilder(appIntercaptor)
                .cache(cache)
                .build();
    }

    @Named(NO_CACHE)
    @Provides
    OkHttpClient provideOkHttpClient(Interceptor appIntercaptor) {
        return getCommonOkHttpBuilder(appIntercaptor)
                .build();
    }

    @NonNull
    private OkHttpClient.Builder getCommonOkHttpBuilder(Interceptor appIntercaptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(appIntercaptor);
        addInterceptors(builder);
        return builder;
    }

    private void addInterceptors(OkHttpClient.Builder builder) {
        for (Interceptor interceptor : mInterceptors) {
            builder.addInterceptor(interceptor);
        }
    }


    @Module
    public interface OkHttpDeclarations {
        @Binds
        Interceptor bindInterceptor(AppHttpInterceptor interceptor);
    }

    @Provides
    public String provideuserAgent() {
        return mUserAgent;
    }
}