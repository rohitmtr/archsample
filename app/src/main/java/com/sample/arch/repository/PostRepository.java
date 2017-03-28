package com.sample.arch.repository;

import com.sample.arch.data.Post;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public interface PostRepository {

    Single<List<Post>> getPosts(int userId);

    Single<Post> getPost(int postId);
}
