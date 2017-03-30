package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;
import com.sample.arch.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public class SelectedUserPostsInteractorImpl implements SelectedUserPostsInteractor {

    private BehaviorSubject<List<Post>> mSelectedUserPosts = BehaviorSubject.create();

    private PostRepository mPostRepository;
    private UserRepository mUserRepository;

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
                        loadPost(user);
                    }
                }, RxUtils.errorDefault());
    }

    @Override
    public Flowable<List<Post>> getPosts() {
        return mSelectedUserPosts.toFlowable(BackpressureStrategy.LATEST);
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
