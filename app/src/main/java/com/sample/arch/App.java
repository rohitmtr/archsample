package com.sample.arch;

import com.sample.arch.di.AppComponent;
import com.sample.arch.di.DaggerAppComponent;
import com.sample.arch.di.Module.AppModule;
import com.sample.arch.di.Module.OkHttpModule;

import android.app.Application;
import android.content.Context;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    public static App get(Context context) {
        return (App)context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = createAppComponentBuilder()
                .build();
    }


    protected DaggerAppComponent.Builder createAppComponentBuilder() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .okHttpModule(new OkHttpModule("android"));
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
