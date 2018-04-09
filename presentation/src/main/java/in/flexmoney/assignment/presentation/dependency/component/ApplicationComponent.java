package in.flexmoney.assignment.presentation.dependency.component;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import in.flexmoney.assignment.data.net.RestApi;
import in.flexmoney.assignment.domain.executor.PostExecutionThread;
import in.flexmoney.assignment.domain.executor.ThreadExecutor;
import in.flexmoney.assignment.domain.repository.CardRepository;
import in.flexmoney.assignment.presentation.dependency.module.ApplicationModule;
import in.flexmoney.assignment.presentation.dependency.module.DataModule;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    Context context();
    SharedPreferences sharedPreferences();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();

    RestApi restApi();
    CardRepository cardRepository();

}
