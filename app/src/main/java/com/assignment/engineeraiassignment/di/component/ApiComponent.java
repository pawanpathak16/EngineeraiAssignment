package com.assignment.engineeraiassignment.di.component;

import android.app.Application;

import com.assignment.engineeraiassignment.AppController;
import com.assignment.engineeraiassignment.di.modules.ActivityModule;
import com.assignment.engineeraiassignment.di.modules.ApiModule;
import com.assignment.engineeraiassignment.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules={ApiModule.class, ViewModelModule.class, ActivityModule.class, AndroidSupportInjectionModule.class})
public interface ApiComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);



        ApiComponent build();

    }
    void inject(AppController appController);
}
