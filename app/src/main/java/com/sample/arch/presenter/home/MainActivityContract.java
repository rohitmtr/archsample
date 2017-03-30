package com.sample.arch.presenter.home;

import com.sample.arch.BaseView;
import com.sample.arch.data.User;
import com.sample.arch.presenter.BasePresenter;

/**
 * Created by rohitkumar.yadav on 30/3/17.
 */

public interface MainActivityContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {
        void onUserChange(User user);
    }
}
