package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.domain.UseCase;

import io.reactivex.Flowable;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

public interface PostDetailInteractor extends UseCase<Post> {

    Flowable<Post> getPost(int postId);
    
}
