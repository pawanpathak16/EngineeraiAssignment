package com.assignment.engineeraiassignment.data.remote.api;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApiService
{

    @GET("users")
    Observable<JsonElement> fetchUsers(@Query("offset") int offset, @Query("limit")int limit);
}
