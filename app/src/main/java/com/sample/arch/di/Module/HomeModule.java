package com.sample.arch.di.Module;

import com.sample.arch.baserecyler.DelegationAdapter;
import com.sample.arch.di.scope.FragmentScope;
import com.sample.arch.domain.post.PostsInteractor;
import com.sample.arch.domain.post.PostsInteractorImpl;
import com.sample.arch.domain.post.SelectedUserPostsInteractorImpl;
import com.sample.arch.domain.post.SelectedUserPostsInteractor;
import com.sample.arch.home.PostDataAdapter;
import com.sample.arch.home.PostDelegateAdapter;
import com.sample.arch.home.PostTitleDelegate;
import com.sample.arch.presenter.home.HomeContract;
import com.sample.arch.presenter.home.HomePresenter;
import com.sample.arch.repository.PostRepository;
import com.sample.arch.repository.PostRepositoryImpl;
import com.sample.arch.rx.RxBinder;
import com.trello.rxlifecycle2.android.FragmentEvent;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

@Module(includes = {HomeModule.Declarations.class})
public class HomeModule {

    private HomeContract.HomeView mView;
    private RxBinder<FragmentEvent> mRxBinder;
    private PostTitleDelegate.PostClickListener mPostClickListener;

    public HomeModule(HomeContract.HomeView view, RxBinder<FragmentEvent> rxBinder, PostTitleDelegate.PostClickListener postClickListener) {
        mView = view;
        mRxBinder = rxBinder;
        mPostClickListener = postClickListener;
    }

    @Module
    public interface Declarations {

        @Binds
        PostRepository bindPostRepository(PostRepositoryImpl postRepository);

        @Binds
        HomeContract.Presenter bindPresenter(HomePresenter presenter);

        @Binds
        SelectedUserPostsInteractor bindSelectedUserPostsInteractor(SelectedUserPostsInteractorImpl userPostsInteractor);

        @Binds
        DelegationAdapter bindPostDelegateAdapter(PostDelegateAdapter postDataAdapter);

    }

    @Provides
    public RxBinder<FragmentEvent> provideBinder() {
        return mRxBinder;
    }

    @Provides
    public HomeContract.HomeView provideView() {
        return mView;
    }

    @Provides
    public RecyclerView.LayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @FragmentScope
    public PostTitleDelegate providePostTitleDelegate() {
        return new PostTitleDelegate(mPostClickListener);
    }

    @Provides
    @FragmentScope
    public PostDataAdapter providePostDataAdapter() {
        return new PostDataAdapter();
    }
}
