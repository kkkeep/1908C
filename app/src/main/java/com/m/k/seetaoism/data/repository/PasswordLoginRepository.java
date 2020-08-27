package com.m.k.seetaoism.data.repository;


import com.m.k.mvp.data.BaseRepository;
import com.m.k.mvp.data.ok.AppDataService;
import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.mvp.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.mvp.data.response.MvpResponse;
import com.trello.rxlifecycle4.LifecycleProvider;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class PasswordLoginRepository extends BaseRepository implements PasswordLoginContract.IPasswordLoginMode {




    @Override
    public void login(LifecycleProvider provider, MvpRequest<User> request, IBaseCallBack<User> back) {
        doObserver(provider, request, AppDataService.getAppApiService().getUser(request.getParams()),back);

    }
}
