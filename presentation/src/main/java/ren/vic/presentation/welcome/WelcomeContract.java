package ren.vic.presentation.welcome;

import ren.vic.presentation.common.BasePresenter;
import ren.vic.presentation.common.BaseView;

public class WelcomeContract {

    interface View extends BaseView {

        void onShowMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void showMessage(String name);
    }
}
