package in.flexmoney.assignment.presentation.view;

import android.content.Context;

public interface BaseView {

    Context context();

    void showLoader();
    void hideLoader();
    void hideKeyboard();
    void handleError(Throwable error);
    void showMessage(String message);
}
