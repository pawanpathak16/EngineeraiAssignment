package com.assignment.engineeraiassignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.assignment.engineeraiassignment.data.remote.api.PostsApiResponse;
import com.assignment.engineeraiassignment.data.remote.api.UsersApiResponse;
import com.assignment.engineeraiassignment.repository.PostsRepository;
import com.assignment.engineeraiassignment.repository.UsersRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UsersViewModel extends ViewModel
{
    private UsersRepository usersRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<UsersApiResponse> usersLiveData = new MutableLiveData<>();

    public MutableLiveData<UsersApiResponse> usersResponse()
    {
        return usersLiveData;
    }
    @Inject
    public UsersViewModel(UsersRepository repository) {
        this.usersRepository = repository;
    }

    public void getUsers(int offset,int limit)
    {
        disposables.add(usersRepository.getPostsByPage(offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> usersLiveData.setValue(UsersApiResponse.loading()))
                .subscribe(
                        result -> usersLiveData.setValue(UsersApiResponse.success(result)),
                        throwable -> usersLiveData.setValue(UsersApiResponse.error(throwable))
                ));

    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
