package in.flexmoney.assignment.presentation.view.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import in.flexmoney.assignment.presentation.BaseApplication;
import in.flexmoney.assignment.presentation.R;
import in.flexmoney.assignment.presentation.dependency.component.FragmentInjector;
import in.flexmoney.assignment.presentation.view.BaseView;

public abstract class CleanActivity extends BaseActivity implements BaseView {

    private FragmentInjector fragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.initializeActivityComponent();
        super.onCreate(savedInstanceState);
    }

    public FragmentInjector getFragmentInjector() {
        return this.fragmentInjector;
    }

    private void initializeActivityComponent() {
        if (this.fragmentInjector == null) {
            this.fragmentInjector = ((BaseApplication)getApplication()).getFragmentInjector();
        }
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(context(), getResources().getString(R.string.message_error),
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(context(), message, Toast.LENGTH_LONG).show();
    }

}
