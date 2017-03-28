package com.sample.arch.rx;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by rohitkumar.yadav on 26/1/17.
 */

public class RxFragment extends Fragment implements RxBinder<FragmentEvent> {

    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLifecycleSubject.onNext(FragmentEvent.ATTACH);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        mLifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        mLifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mLifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mLifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        mLifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }


    @NonNull
    @Override
    public <T> AsyncWithinLifecycleTransformer<T> asyncCallWithinLifecycle() {
        return AsyncWithinLifecycleTransformer.create(this.<T>withinLifecycle());
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> withinLifecycle() {
        return RxLifecycleAndroid.bindFragment(mLifecycleSubject);
    }

    @NonNull
    @Override
    public <T> AsyncCallTransformer<T> asyncCall() {
        return AsyncCallTransformer.create();
    }

    @NonNull
    @Override
    public <T> CompositeTransformer<T> bindUntilEvent(@NonNull FragmentEvent fragmentEvent) {
        LifecycleTransformer<T> lifecycleTransformer = RxLifecycle.bindUntilEvent(mLifecycleSubject, fragmentEvent);
        return AsyncWithinLifecycleTransformer.create(lifecycleTransformer);
    }


    protected void setUpDependencyInjection() {

    }
}
