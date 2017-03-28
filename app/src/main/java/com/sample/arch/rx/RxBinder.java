package com.sample.arch.rx;

import com.trello.rxlifecycle2.LifecycleTransformer;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public interface RxBinder<Event> {


    @NonNull
    @CheckResult
    <T> CompositeTransformer<T> asyncCallWithinLifecycle();

    @NonNull
    @CheckResult
    <T> LifecycleTransformer<T> withinLifecycle();


    @NonNull
    @CheckResult
    <T> CompositeTransformer<T> asyncCall();


    //TODO: need to handle with generic IRxBinder<E>.
    @NonNull
    @CheckResult
    <T> CompositeTransformer<T> bindUntilEvent(@NonNull Event event);


}
