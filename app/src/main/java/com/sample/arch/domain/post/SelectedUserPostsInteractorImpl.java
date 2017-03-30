package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;
import com.sample.arch.rx.RxUtils;

import org.reactivestreams.Subscription;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public class SelectedUserPostsInteractorImpl implements SelectedUserPostsInteractor {

    private BehaviorSubject<List<Post>> mSelectedUserPosts = BehaviorSubject.create();

    private PostRepository mPostRepository;
    private UserRepository mUserRepository;
    private User mUser;

    @Inject
    public SelectedUserPostsInteractorImpl(PostRepository postRepository, UserRepository userRepository) {
        mPostRepository = postRepository;
        mUserRepository = userRepository;
        startObservingUser();
    }


    private void startObservingUser() {
        mUserRepository.getSelected()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mUser = user;
                        if (mSelectedUserPosts.hasObservers()) {
                            loadPost();
                        }
                    }
                }, RxUtils.errorDefault());
    }

    @Override
    public Flowable<List<Post>> getPosts() {
        return mSelectedUserPosts.toFlowable(BackpressureStrategy.LATEST)
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (mUser != null && mSelectedUserPosts.getValue() == null) {
                            loadPost();
                        }
                    }
                });
    }

    private void loadPost() {
        mPostRepository.getPosts(mUser.id())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                mSelectedUserPosts.onNext(posts);
            }
        }, RxUtils.errorDefault());
    }
}
