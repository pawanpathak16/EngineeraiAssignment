package com.assignment.engineeraiassignment.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.engineeraiassignment.factory.ViewModelFactory;
import com.assignment.engineeraiassignment.viewmodel.PostsViewModel;
import com.assignment.engineeraiassignment.viewmodel.UsersViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsPostsviewModel(PostsViewModel postsViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsUsersModel(UsersViewModel usersViewModel);
}
