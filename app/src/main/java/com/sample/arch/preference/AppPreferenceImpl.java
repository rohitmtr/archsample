package com.sample.arch.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class AppPreferenceImpl implements AppPreference {

    private SharedPreferences mSharedPreferences;
    private static final String SELECTED_USER = "userId";

    @Inject
    public AppPreferenceImpl(Context context) {
      mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void setUserId(int userId) {
        mSharedPreferences.edit()
                .putInt(SELECTED_USER, userId)
                .apply();
    }

    @Override
    public int getUserId() {
        return mSharedPreferences.getInt(SELECTED_USER, 1);
    }
}
