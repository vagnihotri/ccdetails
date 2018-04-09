package in.flexmoney.assignment.presentation.dependency.component;

import in.flexmoney.assignment.presentation.dependency.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent extends FragmentInjector {}
