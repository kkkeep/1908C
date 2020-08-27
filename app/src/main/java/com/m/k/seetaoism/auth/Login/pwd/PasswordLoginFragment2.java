package com.m.k.seetaoism.auth.Login.pwd;

import android.view.View;

import com.m.k.seetaoism.R;
import com.m.k.mvp.base.p.BaseSmartPresenter1;
import com.m.k.mvp.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.data.repository.PasswordLoginRepository2;

public class PasswordLoginFragment2 extends BaseSmartFragment1<User> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_password_login;
    }


    @Override
    protected void bindView(View view) {
    }

    @Override
    public void onResult1(MvpResponse<User> response) {

    }


    @Override
    public BaseSmartPresenter1<User, ?> createPresenter() {
        return new BaseSmartPresenter1<>(new PasswordLoginRepository2());
    }
}
