package com.m.k.seetaoism.auth.Login.pwd;

import com.m.k.mvp.data.request.PostRequest;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.mvp.base.IBaseCallBack;
import com.m.k.mvp.base.p.BasePresenter;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.data.repository.PasswordLoginRepository;
import com.m.k.mvp.utils.MvpUtils;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.HashMap;

public class PasswordLoginPresenter extends BasePresenter<PasswordLoginContract.IPasswordLoginView> implements PasswordLoginContract.IPasswordLoginPresenter {


    private PasswordLoginContract.IPasswordLoginMode mode;

    public PasswordLoginPresenter() {
        mode = new PasswordLoginRepository();
    }

    @Override
    public void login(String account, String password) {

        if(!MvpUtils.isValidUserCount(account)){
            mView.onInputError(getMvpContent().getResources().getString(R.string.text_error_invalide_phone_number));
            return;
        }
        if(!MvpUtils.isValidUserPasssword(password)){
            mView.onInputError(getMvpContent().getResources().getString(R.string.text_error_invalide_password));
            return;
        }

        PostRequest<User> request = new PostRequest<>(Constrant.URL.LOGIN);


        HashMap<String,Object> params = ParamsUtils.getCommonParams();
        params.put(Constrant.RequestKey.KEY_USER_ACCOUNT,account);
        params.put(Constrant.RequestKey.KEY_USER_PASSWORD,password);

        request.setParams(params);

        mView.onShowLoading();
        mode.login(getLifecycleProvider(),request, new IBaseCallBack<User>() {
            @Override
            public void onResult(MvpResponse<User> response) {
                if(mView != null){
                    mView.onCloseLoading();
                mView.onUserResult(response);
            }

            }
        });


    }

    @Override
    public boolean cancelRequest() {

        return false;
    }
}
