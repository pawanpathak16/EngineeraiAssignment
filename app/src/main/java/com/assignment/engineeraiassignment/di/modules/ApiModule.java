package com.assignment.engineeraiassignment.di.modules;

import android.app.Application;

import com.assignment.engineeraiassignment.AppConstants;
import com.assignment.engineeraiassignment.data.remote.api.PostsApiService;
import com.assignment.engineeraiassignment.data.remote.api.UsersApiService;
import com.assignment.engineeraiassignment.data.remote.interceptor.NetworkInterceptor;
import com.assignment.engineeraiassignment.data.remote.interceptor.RequestInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Cache provideCache(Application application) {
        long cacheSize = 10 * 1024 * 1024; // 10 MB
        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
        return new Cache(httpCacheDirectory, cacheSize);
    }


    @Provides
    @Singleton
    NetworkInterceptor provideNetworkInterceptor(Application application) {
        return new NetworkInterceptor(application.getApplicationContext());
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, NetworkInterceptor networkInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
        httpClient.addInterceptor(networkInterceptor);
        httpClient.addInterceptor(logging);
        httpClient.addNetworkInterceptor(new RequestInterceptor());
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    @Singleton
    @Named("posts")
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL_POSTS)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    @Named("users")
    Retrofit provideRetrofit2(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL_USERS)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    PostsApiService provideMovieApiService(@Named("posts") Retrofit retrofit) {
        return retrofit.create(PostsApiService.class);
    }

    @Provides
    @Singleton
    UsersApiService provideUsersApiService(@Named("users") Retrofit retrofit) {
        return retrofit.create(UsersApiService.class);
    }



}
