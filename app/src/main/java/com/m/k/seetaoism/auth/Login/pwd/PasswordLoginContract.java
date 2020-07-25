package com.m.k.seetaoism.auth.Login.pwd;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.base.v.IBaseView;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.MvpRequest;

public interface PasswordLoginContract {

    public interface ILoginView extends IBaseView<ILoginPresenter> {
        void onLoginSuccess(User user);
        void onLoginFail(String msg);

        void onRegisterSuccess(User user);
        void onRegisterFail(String user);
        void onForgetSuccess(String msg);
        void onForgetFail(String msg);

        void onNetError();
        void onInputFail(String msg);
        void showLoading();
        void closeLoading();

    }

    public interface ILoginPresenter extends IBasePresenter<ILoginView>{

        void login(String count,String password);
        void register(String count,String password,String code);
        void forgetPassword(String count,String password,String confirmPassword);
    }


    public interface  ILoginMode {

        void login(MvpRequest request, IBaseCallBack<User> callBack);
        void register (MvpRequest request, IBaseCallBack<User> callBack);
        void forgetPassword (MvpRequest request, IBaseCallBack<User> callBack);
    }
}
