package com.m.k.seetaoism.auth.Login.pwd;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.repository.UserRepository;
import com.m.k.seetaoism.utils.AppUtils;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.HashMap;

import static com.m.k.seetaoism.Constrant.RequestKey.*;

public class PasswordLoginPresenter implements PasswordLoginContract.ILoginPresenter {

    private PasswordLoginContract.ILoginView mView;

    private PasswordLoginContract.ILoginMode mMode;


    public PasswordLoginPresenter(){
        mMode = new UserRepository();
    }
    @Override
    public void login(String userCount, String password) {
        if(!isOnInternet()){
            mView.onNetError();
            return;
        }

        if(!AppUtils.isValidUserCount(userCount)){
            mView.onInputFail("手机号格式不对，手机号长度为11位");

            return;
        }


        if(!AppUtils.isValidUserPasssword(password)){
            mView.onInputFail("密码格式不对，密码长度必须大于等于6");
            return;
        }

        HashMap params = ParamsUtils.getCommonParams();
        params.put(KEY_USER_ACOUNT,userCount);
        params.put(KEY_USER_PASSWORD,password);


        mView.showLoading();
        mMode.login(params, new IBaseCallBack<User>() {
            @Override
            public void onSuccess(User data) {
                if(mView != null){
                    mView.closeLoading();
                    mView.onLoginSuccess(data);
                }
            }

            @Override
            public void onError(String msg) {
                if(mView != null){
                    mView.closeLoading();
                    mView.onLoginFail(msg);
                }
            }
        });
    }
    private boolean isOnInternet(){
        return true;
    }
    @Override
    public void bindView(PasswordLoginContract.ILoginView view) {
        mView = view;
    }

    @Override
    public void unBindView() {
        mView = null;
    }



}
