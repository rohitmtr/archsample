package com.sample.arch.di.Module;

import com.sample.arch.preference.AppPreference;
import com.sample.arch.preference.AppPreferenceImpl;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@Module(includes = {AppModule.Declarations.class})
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }


    @Module
    public interface Declarations {
        @Binds
        @Singleton
        AppPreference bindPreferences(AppPreferenceImpl appPreference);
    }

}
