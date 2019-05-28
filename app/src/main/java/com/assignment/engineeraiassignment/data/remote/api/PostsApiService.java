package com.assignment.engineeraiassignment.data.remote.api;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostsApiService
{

    @GET("search_by_date")
    Observable<JsonElement> fetchPostsByTagAndPage(@Query("tags") String tag,@Query("page")int value);
}
