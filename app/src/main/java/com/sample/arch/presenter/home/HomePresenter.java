package com.sample.arch.presenter.home;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.domain.post.PostsInteractor;
import com.sample.arch.domain.post.PostsInteractorImpl;
import com.sample.arch.domain.post.SelectedUserPostsInteractor;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.home.PostDataAdapter;
import com.sample.arch.rx.RxBinder;
import com.sample.arch.rx.RxUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.reactivestreams.Publisher;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.HomeView mHomeView;
    private RxBinder<FragmentEvent> mRxBinder;
    private SelectedUserPostsInteractor mUserPostsInteractor;
    private SetGetUserInteractor mSetGetUserInteractor;
    private PostDataAdapter mPostDataAdapter;

    @Inject
    public HomePresenter(@NonNull HomeContract.HomeView homeView,
                         @NonNull RxBinder<FragmentEvent> rxBinder,
                         @NonNull SelectedUserPostsInteractor userPostsInteractor,
                         @NonNull SetGetUserInteractor setGetUserInteractor,
                         @NonNull PostDataAdapter postDataAdapter) {

        mHomeView = homeView;
        mRxBinder = rxBinder;
        mUserPostsInteractor = userPostsInteractor;
        mSetGetUserInteractor = setGetUserInteractor;
        mPostDataAdapter = postDataAdapter;

        startObservingSelectedUser();
        startObservingUserPosts();
    }

    private void startObservingSelectedUser() {
        mSetGetUserInteractor.getSelectedUser()
                .compose(mRxBinder.<User>asyncCallWithinLifecycle())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mHomeView.showLoader();
                    }
                });
    }

    private void startObservingUserPosts() {
        mUserPostsInteractor.getPosts()
                .compose(mRxBinder.<List<Post>>asyncCallWithinLifecycle())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        mPostDataAdapter.setPage(posts);
                        mHomeView.hideLoader();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mHomeView.hideLoader();
                        Log.e("HomePresenter:", "", throwable);
                    }
                });
    }
}
