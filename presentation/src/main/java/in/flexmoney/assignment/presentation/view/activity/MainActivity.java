package in.flexmoney.assignment.presentation.view.activity;

import android.os.Bundle;

import in.flexmoney.assignment.presentation.R;
import in.flexmoney.assignment.presentation.view.activity.base.CleanActivity;
import in.flexmoney.assignment.presentation.view.fragment.SubmitCardDetailsFragment;

public class MainActivity extends CleanActivity {

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new SubmitCardDetailsFragment());
        }
    }

    @Override
    protected boolean useBackToolbar() {
        return false;
    }
}
