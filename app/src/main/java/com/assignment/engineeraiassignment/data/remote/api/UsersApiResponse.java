package com.assignment.engineeraiassignment.data.remote.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonElement;

import static com.assignment.engineeraiassignment.data.remote.api.Status.ERROR;
import static com.assignment.engineeraiassignment.data.remote.api.Status.LOADING;
import static com.assignment.engineeraiassignment.data.remote.api.Status.SUCCESS;

public class UsersApiResponse
{
    public final Status status;

    @Nullable
    public final JsonElement data;

    @Nullable
    public final Throwable error;

    private UsersApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static UsersApiResponse loading() {
        return new UsersApiResponse(LOADING, null, null);
    }

    public static UsersApiResponse success(@NonNull JsonElement data) {
        return new UsersApiResponse(SUCCESS, data, null);
    }

    public static UsersApiResponse error(@NonNull Throwable error) {
        return new UsersApiResponse(ERROR, null, error);
    }




}
