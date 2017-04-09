package com.sample.arch.domain.user;

import com.sample.arch.data.User;
import com.sample.arch.domain.UseCase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

public interface UserInteractor extends UseCase<List<User>> {

    Observable<List<User>> getUsers();
}
