package in.flexmoney.assignment.presentation.view.activity.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.flexmoney.assignment.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.bind(this);
        this.initializeActivity(savedInstanceState);
        this.initializeToolbar();
    }

    protected abstract void initializeActivity(Bundle savedInstanceState);

    protected void initializeToolbar(){
        if (this.useToolbar()) {
            setSupportActionBar(this.toolbar);
            if (this.useBackToolbar()) {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        } else toolbar.setVisibility(View.GONE);
    }

    protected boolean useToolbar() { return true; }
    protected boolean useBackToolbar() { return true; }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public Context context() {
        return getApplicationContext();
    }

    protected Toolbar getToolbar() {
        return this.toolbar;
    }

    public boolean isLoaderShowing() {
        if (this.progressDialog == null) return false;
        return this.progressDialog.isShowing();
    }

    public void showLoader() {
        if (this.progressDialog == null) this.progressDialog = new ProgressDialog(this);
        this.progressDialog.show();
    }

    public void hideLoader() {
        if (this.progressDialog != null) this.progressDialog.dismiss();
        this.progressDialog = null;
    }

}
