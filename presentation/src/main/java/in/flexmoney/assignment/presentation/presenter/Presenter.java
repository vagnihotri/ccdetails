package in.flexmoney.assignment.presentation.presenter;

import in.flexmoney.assignment.presentation.view.BaseView;

public interface Presenter {

    void initWithView(BaseView view);
    void resume();
    void pause();
    void destroy();

}
