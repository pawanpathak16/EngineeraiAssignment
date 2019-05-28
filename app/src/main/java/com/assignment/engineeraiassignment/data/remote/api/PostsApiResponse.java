package com.assignment.engineeraiassignment.data.remote.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonElement;

import static com.assignment.engineeraiassignment.data.remote.api.Status.ERROR;
import static com.assignment.engineeraiassignment.data.remote.api.Status.LOADING;
import static com.assignment.engineeraiassignment.data.remote.api.Status.SUCCESS;

public class PostsApiResponse
{
    public final Status status;

    @Nullable
    public final JsonElement data;

    @Nullable
    public final Throwable error;

    private PostsApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static PostsApiResponse loading() {
        return new PostsApiResponse(LOADING, null, null);
    }

    public static PostsApiResponse success(@NonNull JsonElement data) {
        return new PostsApiResponse(SUCCESS, data, null);
    }

    public static PostsApiResponse error(@NonNull Throwable error) {
        return new PostsApiResponse(ERROR, null, error);
    }


}
