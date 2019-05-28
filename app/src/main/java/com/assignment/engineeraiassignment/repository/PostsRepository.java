package com.assignment.engineeraiassignment.repository;

import com.assignment.engineeraiassignment.data.remote.api.PostsApiResponse;
import com.assignment.engineeraiassignment.data.remote.api.PostsApiService;
import com.google.gson.JsonElement;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PostsRepository {

    private PostsApiService postsApiService;
    @Inject
    public PostsRepository(PostsApiService postsApiService)
    {
        this.postsApiService=postsApiService;
    }

    public Observable<JsonElement> getPostsByPage(String tag,int page)
    {
        return postsApiService.fetchPostsByTagAndPage(tag,page);
    }
}
