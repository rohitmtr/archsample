package com.sample.arch.rx;

import com.trello.rxlifecycle2.LifecycleTransformer;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AsyncWithinLifecycleTransformer<T> implements CompositeTransformer<T> {

    final LifecycleTransformer<T> mLifeCycleTransformer;

    public AsyncWithinLifecycleTransformer(LifecycleTransformer<T> lifeCycleTransformer) {
        mLifeCycleTransformer = lifeCycleTransformer;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return mLifeCycleTransformer.apply(
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return mLifeCycleTransformer.apply(
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return mLifeCycleTransformer.apply(
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return mLifeCycleTransformer.apply(
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return mLifeCycleTransformer.apply(
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    public static <T> AsyncWithinLifecycleTransformer<T> create(LifecycleTransformer<T> lifecycleTransformer) {
        return new AsyncWithinLifecycleTransformer<>(lifecycleTransformer);
    }
}