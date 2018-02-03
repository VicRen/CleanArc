package ren.vic.presentation.internal.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ren.vic.presentation.welcome.WelcomeActivity;
import ren.vic.presentation.internal.di.scope.ActivityScoped;

@Module
public abstract class BindingActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = BlankActivityModule.class)
    abstract WelcomeActivity welcomeActivity();

}
