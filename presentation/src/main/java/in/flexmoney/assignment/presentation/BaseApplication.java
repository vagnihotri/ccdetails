package in.flexmoney.assignment.presentation;

import android.app.Application;

import in.flexmoney.assignment.presentation.dependency.component.ApplicationComponent;
import in.flexmoney.assignment.presentation.dependency.component.DaggerActivityComponent;
import in.flexmoney.assignment.presentation.dependency.component.DaggerApplicationComponent;
import in.flexmoney.assignment.presentation.dependency.component.FragmentInjector;
import in.flexmoney.assignment.presentation.dependency.module.ApplicationModule;

public class BaseApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(new ApplicationModule(this))
                                        .build();
    }

    public FragmentInjector getFragmentInjector() {
        return DaggerActivityComponent.builder()
                .applicationComponent(this.applicationComponent).build();
    }

}
