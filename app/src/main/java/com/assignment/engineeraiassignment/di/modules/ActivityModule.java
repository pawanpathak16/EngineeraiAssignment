package com.assignment.engineeraiassignment.di.modules;

import com.assignment.engineeraiassignment.ui.HomeActivity;
import com.assignment.engineeraiassignment.ui.ImageAssignmentActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract HomeActivity contributesHomeActivity();
    @ContributesAndroidInjector()
    abstract ImageAssignmentActivity contributesImagesAssignmentActivity();


}
