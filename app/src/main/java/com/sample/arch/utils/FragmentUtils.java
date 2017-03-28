package com.sample.arch.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class FragmentUtils {

    public static int replace(FragmentManager manager, int containerId, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerId, fragment);
        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        return transaction.commit();
    }

    public static int replaceAddToStack(FragmentManager manager, int containerId, Fragment fragment) {
        return replace(manager,containerId,fragment,true);
    }
}
