package com.sample.arch.rx;

import android.util.Log;

import io.reactivex.functions.Consumer;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class RxUtils {


    private static Consumer<Throwable> DEFAULT_ERROR = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            Log.e("Sample","Arch:",throwable);
        }
    };


    public static Consumer<Throwable> errorDefault() {
        return DEFAULT_ERROR;
    }

}
