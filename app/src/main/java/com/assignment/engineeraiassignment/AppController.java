package com.assignment.engineeraiassignment;

import android.app.Activity;
import android.app.Application;


import com.assignment.engineeraiassignment.di.component.DaggerApiComponent;
import com.assignment.engineeraiassignment.di.modules.ApiModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class AppController extends Application implements HasActivityInjector
{

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApiComponent.builder()
                .application(this)
                .apiModule(new ApiModule())
                .build()
                .inject(this);
    }
}
