package com.m.k.seetaoism.auth.Login.pwd;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.User;

import java.util.HashMap;
import java.util.Map;

public interface PasswordLoginContract {

    public interface ILoginView{
        void onLoginSuccess(User user);
        void onLoginFail(String msg);

        void onNetError();
        void onInputFail(String msg);
        void showLoading();
        void closeLoading();

    }

    public interface ILoginPresenter{
        void login(String userCount,String password);
        void bindView(ILoginView view);
        void unBindView();
    }

    public interface  ILoginMode{
        void login(HashMap<String, String> params, IBaseCallBack<User> callBack);
    }
}
