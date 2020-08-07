package com.m.k.seetaoism.auth.Login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.mvp.data.entity.IUser;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.code.CodeLoginFragment;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginFragment;
import com.m.k.seetaoism.base.BaseActivity;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.manager.MvpFragmentManager;

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
