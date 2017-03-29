package com.sample.arch.presenter.home;

import com.sample.arch.data.Post;
import com.sample.arch.data.User;
import com.sample.arch.domain.post.PostsInteractor;
import com.sample.arch.domain.post.PostsInteractorImpl;
import com.sample.arch.domain.user.SetGetUserInteractor;
import com.sample.arch.home.PostDataAdapter;
import com.sample.arch.rx.RxBinder;
import com.sample.arch.rx.RxUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.HomeView mHomeView;
    private RxBinder<FragmentEvent> mRxBinder;
    private PostsInteractor mPostsInteractor;
    private SetGetUserInteractor mSetGetUserInteractor;
    private PostDataAdapter mPostDataAdapter;


    @Inject
    public HomePresenter(HomeContract.HomeView homeView, RxBinder<FragmentEvent> rxBinder, PostsInteractor postsInteractor, SetGetUserInteractor setGetUserInteractor, PostDataAdapter postDataAdapter) {
        mHomeView = homeView;
        mRxBinder = rxBinder;
        mPostsInteractor = postsInteractor;
        mSetGetUserInteractor = setGetUserInteractor;
        mPostDataAdapter = postDataAdapter;

        mSetGetUserInteractor.getSelectedUser()
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mHomeView.showLoader();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<User, Publisher<List<Post>>>() {
                    @Override
                    public Publisher<List<Post>> apply(User user) throws Exception {
                        return  mPostsInteractor.getPosts(user.id());
                    }
                })
                .compose(mRxBinder.<List<Post>>asyncCallWithinLifecycle())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        mPostDataAdapter.setPage(posts);
                        mHomeView.hideLoader();
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mHomeView.hideLoader();
                    }
                });
    }

}
