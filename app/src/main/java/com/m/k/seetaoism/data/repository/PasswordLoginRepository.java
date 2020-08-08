package com.m.k.seetaoism.data.repository;


import com.m.k.JDApplication;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.mvp.utils.MvpSpUtils;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.MvpRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.trello.rxlifecycle4.LifecycleProvider;

import io.reactivex.rxjava3.functions.Consumer;

public class PasswordLoginRepository extends BaseRepository implements PasswordLoginContract.IPasswordLoginMode {




    @Override
    public void login(LifecycleProvider provider,MvpRequest<User> request, IBaseCallBack<User> back) {

        doRequest(provider,request, new Consumer<MvpResponse<User>>() {
            @Override
            public void accept(MvpResponse<User> userMvpResponse) throws Throwable {

                    if(userMvpResponse.isOk()){
                        User user = userMvpResponse.getData();
                        MvpUserManager.login(user);

                    }
            }
        },back);


    }
}
