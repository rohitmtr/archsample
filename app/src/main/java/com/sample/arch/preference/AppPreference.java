package com.sample.arch.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public interface AppPreference {

     void setUserId(int userId);

    int getUserId();
}
