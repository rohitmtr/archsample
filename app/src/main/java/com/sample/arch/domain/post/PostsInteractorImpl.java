package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.domain.UseCase;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

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
    public Flowable<List<Post>> getPosts(int userId) {
        return mPostRepository.getPosts(userId).toFlowable();
    }
}
