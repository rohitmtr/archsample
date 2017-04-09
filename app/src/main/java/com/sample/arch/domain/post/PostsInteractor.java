package com.sample.arch.domain.post;

import com.sample.arch.data.Post;
import com.sample.arch.domain.UseCase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by rohitkumar.yadav on 28/3/17.
 */

public interface PostsInteractor extends UseCase<Post> {

    Observable<List<Post>> getPosts(int userId);

}
