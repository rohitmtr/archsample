package com.sample.arch.presenter.home;

import com.sample.arch.BaseView;
import com.sample.arch.data.Post;
import com.sample.arch.presenter.BasePresenter;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

/**
 * Created by rohitkumar.yadav on 27/3/17.
 */

public interface HomeContract {

    interface Presenter extends BasePresenter {

    }

    interface HomeView extends BaseView<Presenter> {
        void showLoader();
        void hideLoader();
    }
}
