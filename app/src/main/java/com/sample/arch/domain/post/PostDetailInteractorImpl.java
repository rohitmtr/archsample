package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.domain.UseCase;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public class PostDetailInteractorImpl implements PostDetailInteractor {

    private PostRepository mPostRepository;

    @Inject
    public PostDetailInteractorImpl(PostRepository postRepository) {
        mPostRepository = postRepository;
    }

    @Override
    public Flowable<Post> getPost(int postId) {
        return mPostRepository.getPost(postId).toFlowable();
    }
}
