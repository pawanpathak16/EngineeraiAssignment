package com.assignment.engineeraiassignment.repository;

import com.assignment.engineeraiassignment.data.remote.api.PostsApiService;
import com.assignment.engineeraiassignment.data.remote.api.UsersApiService;
import com.google.gson.JsonElement;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UsersRepository
{
    private UsersApiService usersApiService;
    @Inject
    public UsersRepository(UsersApiService usersApiService)
    {
        this.usersApiService=usersApiService;
    }

    public Observable<JsonElement> getPostsByPage(int offset,int limit )
    {
        return usersApiService.fetchUsers(offset,limit);
    }


}
