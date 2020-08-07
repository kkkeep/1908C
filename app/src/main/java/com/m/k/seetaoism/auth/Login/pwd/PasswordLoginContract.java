package com.m.k.seetaoism.auth.Login.pwd;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.base.v.IBaseView;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.MvpRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.trello.rxlifecycle4.LifecycleProvider;

public interface PasswordLoginContract {



    interface IPasswordLoginView extends IBaseView<IPasswordLoginPresenter>{

        void onUserResult(MvpResponse<User> response);

        void onInputError(String message);
        void onShowLoading();
        void onCloseLoading();
        

    }


    interface IPasswordLoginPresenter extends IBasePresenter<IPasswordLoginView>{

        void login(String userName,String password);
    }



    interface IPasswordLoginMode {

        void login(LifecycleProvider provider,MvpRequest<User> request, IBaseCallBack<User> back);
    }

}
