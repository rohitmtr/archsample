package com.sample.arch.rx;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by rohitkumar.yadav on 9/4/17.
 */

public class RxUtilsTest {

    @Test
    public void testSubscriptionCount() {
        int subscription = 10;
        final AtomicInteger atomicInteger = new AtomicInteger();
        CountSubjectObserver<Integer> mSelectedUserPosts = CountSubjectObserver.create(BehaviorSubject.<Integer>create());
        mSelectedUserPosts.counter().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                atomicInteger.set(integer);
            }
        });

        ArrayList<Disposable> compositeDisposable = new ArrayList<>();
        for (int i = 0; i < subscription; i++) {
            compositeDisposable.add(mSelectedUserPosts.observable().subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    System.out.println("" + o);
                }
            }));
            Assert.assertEquals(atomicInteger.get(), i + 1);
        }
        mSelectedUserPosts.onNext(1);
        mSelectedUserPosts.onNext(2);

        for (int i = compositeDisposable.size() - 1; i >= 0; i--) {
            compositeDisposable.get(i).dispose();
            Assert.assertEquals(atomicInteger.get(), i);
        }
    }
}
