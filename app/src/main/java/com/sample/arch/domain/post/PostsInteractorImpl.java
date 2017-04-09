package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.domain.UseCase;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class PostsInteractorImpl implements PostsInteractor {

    private PostRepository mPostRepository;

    @Inject
    public PostsInteractorImpl(PostRepository postRepository) {
        mPostRepository = postRepository;
    }


    @Override
    public Observable<List<Post>> getPosts(int userId) {
        return mPostRepository.getPosts(userId).toObservable();
    }
}
