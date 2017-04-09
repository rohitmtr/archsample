package com.sample.arch.rx;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.Subject;

/**
 * Created by rohitkumar.yadav on 9/4/17.
 *
 * There might be some RxJava Functions to achieve the same functionality, need to figure out.
 *
 */

public class CountSubjectObserver<T> extends DisposableObserver<T> {

    private final AtomicInteger mCount = new AtomicInteger();
    private ObservableEmitter<Integer> mEmitter;

    private Observable<Integer> mCountObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            mEmitter = e;
        }
    });

    private Observer<T> mSubject;
    private Observable<T> mObservable;

    public static <T> CountSubjectObserver<T> create(Subject<T> subject) {
        return new CountSubjectObserver<>(subject);
    }

    private CountSubjectObserver(Subject<T> subject) {
        mSubject = subject;
        mObservable = subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mCount.incrementAndGet();
                emitNext();
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                mCount.decrementAndGet();
                emitNext();
            }
        });
    }

    private void emitNext() {
        if (mEmitter != null) {
            mEmitter.onNext(mCount.get());
        }
    }

    @Override
    public void onNext(T value) {
        mSubject.onNext(value);
    }

    @Override
    public void onError(Throwable e) {
        mSubject.onError(e);
    }

    @Override
    public void onComplete() {
        mSubject.onComplete();
    }

    public Observable<Integer> counter() {
        return mCountObservable;
    }

    public Observable<T> observable() {
        return mObservable;
    }
}
