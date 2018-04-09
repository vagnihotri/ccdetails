package in.flexmoney.assignment.presentation;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import in.flexmoney.assignment.presentation.dependency.ActivityScope;
import in.flexmoney.assignment.presentation.presenter.SubmitDetailsPresenter;

@Module
public class TestMockerModule {

    @Provides
    @ActivityScope
    SubmitDetailsPresenter provideSubmitDetailsPresenter() {
        return Mockito.mock(SubmitDetailsPresenter.class);
    }

}
