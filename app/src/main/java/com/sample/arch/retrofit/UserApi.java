package com.sample.arch.retrofit;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public interface UserApi {

    @GET("/users")
    Single<List<User>> getUsers();

    @GET("/posts")
    Single<List<Post>> getPosts(@Query("userId") int userId);

}
