package com.m.k.seetaoism.auth.Login.pwd;

import android.content.Context;
import android.text.TextUtils;

import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.base.IBaseMode;
import com.m.k.seetaoism.base.p.BasePresenter;
import com.m.k.seetaoism.data.BaseRepository;
import com.m.k.seetaoism.data.PostRequest;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.MvpRequest;
import com.m.k.seetaoism.utils.AppUtils;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.HashMap;

public class PasswordLoginPresenter extends BasePresenter<PasswordLoginContract.ILoginView> implements PasswordLoginContract.ILoginPresenter{

    private IBaseMode mMode;

    public PasswordLoginPresenter() {
        mMode = new BaseRepository();
    }

    @Override
    public void login(String count, String password) {

        if(!AppUtils.isOnInternet(getMvpContent())){

            mView.onNetError();
            return;
        }

        if(!AppUtils.isValidUserCount(count)){
            mView.onInputFail("用户名格式不对");
            return;
        }

        if(!AppUtils.isValidUserPasssword(password)){
            mView.onInputFail("密码格式不对");
            return;
        }

        mView.showLoading();

        PostRequest request = new PostRequest();

        HashMap hashMap = ParamsUtils.getCommonParams();

        hashMap.put(Constrant.RequestKey.KEY_USER_ACOUNT,count);
        hashMap.put(Constrant.RequestKey.KEY_USER_PASSWORD,password);

        request.setParams(hashMap);

        mMode.doRequest(request, new IBaseCallBack<User>() {
            @Override
            public void onSuccess(User data) {

            }

            @Override
            public void onError(String msg) {

            }
        });

    }

    @Override
    public void register(String count, String password, String code) {

        if(!AppUtils.isOnInternet(getMvpContent())){

            mView.onNetError();
            return;
        }

        if(!AppUtils.isValidUserCount(count)){
            mView.onInputFail("用户名格式不对");
            return;
        }

        if(!AppUtils.isValidUserPasssword(password)){
            mView.onInputFail("密码格式不对");
            return;
        }
        if(TextUtils.isEmpty(code) || code.length() != 6){
            mView.onInputFail("验证格式不对");
            return;
        }


        mView.showLoading();

        PostRequest request = new PostRequest();

        HashMap hashMap = ParamsUtils.getCommonParams();

        hashMap.put(Constrant.RequestKey.KEY_USER_ACOUNT,count);
        hashMap.put(Constrant.RequestKey.KEY_USER_PASSWORD,password);
        hashMap.put(Constrant.RequestKey.KEY_CODE,code);

        request.setParams(hashMap);


        mMode.doRequest(request, new IBaseCallBack<User>() {
            @Override
            public void onSuccess(User data) {
                mView.onLoginSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.onInputFail(msg);
            }
        });
    }

    @Override
    public void forgetPassword(String count, String password, String confirmPassword) {

    }


}
