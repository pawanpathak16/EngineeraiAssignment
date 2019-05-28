package com.assignment.engineeraiassignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.engineeraiassignment.data.remote.api.PostsApiResponse;
import com.assignment.engineeraiassignment.repository.PostsRepository;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel
{
    private PostsRepository postsRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<PostsApiResponse> postsLiveData = new MutableLiveData<>();

    public MutableLiveData<PostsApiResponse> weatherResponse() {
        return postsLiveData;
    }
    @Inject
    public PostsViewModel(PostsRepository repository) {
        this.postsRepository = repository;
    }

    public void getPostsByPage(int page)
    {
        disposables.add(postsRepository.getPostsByPage("story",page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> postsLiveData.setValue(PostsApiResponse.loading()))
                .subscribe(
                        result -> postsLiveData.setValue(PostsApiResponse.success(result)),
                        throwable -> postsLiveData.setValue(PostsApiResponse.error(throwable))
                ));

    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
