package com.sample.arch.domain.post;

import com.sample.arch.data.Post;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public interface SelectedUserPostsInteractor {
    public Flowable<List<Post>> getPosts();
}
