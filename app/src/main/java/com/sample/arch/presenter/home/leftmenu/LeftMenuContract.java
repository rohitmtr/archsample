package com.sample.arch.presenter.home.leftmenu;

import com.sample.arch.BaseView;
import com.sample.arch.data.User;
import com.sample.arch.presenter.BasePresenter;

/**
 * Created by rohitkumar.yadav on 29/3/17.
 */

public interface LeftMenuContract {

    interface Presenter extends BasePresenter {
        void loadUser();
        void selectUser(User user);
    }

    interface View extends BaseView<Presenter> {
        void showLoader();
        void hideLoader();
    }
}
