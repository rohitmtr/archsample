package com.sample.arch.rx;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Base activity with rx lifecycle binder
 */
public class RxActivity extends AppCompatActivity implements RxBinder<ActivityEvent> {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setUpDependencyInjection();
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        mLifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mLifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();

    }

    @Override
    @NonNull
    @CheckResult
    public <T> LifecycleTransformer<T> withinLifecycle() {
        return RxLifecycleAndroid.bindActivity(mLifecycleSubject);
    }

    @Override
    @NonNull
    @CheckResult
    public <T> CompositeTransformer<T> asyncCall() {
        return AsyncCallTransformer.create();
    }

    @NonNull
    @Override
    public <T> CompositeTransformer<T> asyncCallWithinLifecycle() {
        return AsyncWithinLifecycleTransformer.create(this.<T>withinLifecycle());
    }


    @NonNull
    @Override
    public <T> CompositeTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        LifecycleTransformer<T> lifecycleTransformer = RxLifecycle.bindUntilEvent(mLifecycleSubject, event);
        return AsyncWithinLifecycleTransformer.create(lifecycleTransformer);
    }

    protected void setUpDependencyInjection() {

    }


}
