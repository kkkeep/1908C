package com.m.k.seetaoism.auth.Login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.mvp.manager.MvpFragmentManager;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.code.CodeLoginFragment;
import com.m.k.mvp.base.BaseActivity;
import com.m.k.seetaoism.data.entity.User;

public class AuthActivity extends BaseActivity {

    private MvpUserManager.IUserCallBack<User> userCallBack;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //showToast(MvpUserManager.getToken());

        MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(), CodeLoginFragment.class,null,R.id.auth_fragment_container);


        userCallBack  = MvpUserManager.registerUserStateCallBack(new MvpUserManager.IUserCallBack<User>() {
            @Override
            public void onUserLogin(User user) {
                String token = MvpUserManager.getToken();
                showToast(token);

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
