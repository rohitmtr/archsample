package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;
import com.sample.arch.rx.CountSubjectObserver;
import com.sample.arch.rx.RxUtils;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public class SelectedUserPostsInteractorImpl implements SelectedUserPostsInteractor {

    private CountSubjectObserver<List<Post>> mSelectedUserPosts = CountSubjectObserver.create(BehaviorSubject.<List<Post>>create());

    private PostRepository mPostRepository;
    private UserRepository mUserRepository;
    private Disposable mUserDisposable;

    @Inject
    public SelectedUserPostsInteractorImpl(PostRepository postRepository, UserRepository userRepository) {
        mPostRepository = postRepository;
        mUserRepository = userRepository;
        mSelectedUserPosts.counter()
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                if (integer == 1) { //if one or more observer then keep observing user changes.
                    startObservingUser();
                } else if (integer == 0) {
                    stopObservingUser();
                }
            }
        });
    }


    private void stopObservingUser() {
        if (mUserDisposable != null) {
            mUserDisposable.dispose();
        }
    }

    private void startObservingUser() {
            mUserDisposable = mUserRepository.getSelected()
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<User>() {
                        @Override
                        public void accept(User user) throws Exception {
                            loadPost(user);
                        }
                    }, RxUtils.errorDefault());
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return mSelectedUserPosts.observable();
    }

    private void loadPost(User user) {
        mPostRepository.getPosts(user.id())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                mSelectedUserPosts.onNext(posts);
            }
        }, RxUtils.errorDefault());
    }
}
