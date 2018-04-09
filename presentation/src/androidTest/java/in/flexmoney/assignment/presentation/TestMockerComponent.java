package in.flexmoney.assignment.presentation;

import in.flexmoney.assignment.presentation.dependency.ActivityScope;
import in.flexmoney.assignment.presentation.dependency.component.ApplicationComponent;
import in.flexmoney.assignment.presentation.dependency.component.FragmentInjector;

import dagger.Component;

@ActivityScope
@Component(modules = TestMockerModule.class, dependencies = ApplicationComponent.class)
public interface TestMockerComponent extends FragmentInjector {}
