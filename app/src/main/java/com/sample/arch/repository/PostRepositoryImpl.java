package com.sample.arch.repository;

import com.sample.arch.data.Post;
import com.sample.arch.retrofit.UserApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class PostRepositoryImpl implements PostRepository {

    private UserApi mUserApi;

    @Inject
    public PostRepositoryImpl(UserApi userApi) {
        mUserApi = userApi;
    }

    @Override
    public Single<List<Post>> getPosts(int userId) {
        return mUserApi.getPosts(userId);
    }

}
