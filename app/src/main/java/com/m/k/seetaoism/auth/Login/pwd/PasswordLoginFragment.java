package com.m.k.seetaoism.auth.Login.pwd;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.p.BaseSmartPresenter1;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.base.v.MvpBaseFragment;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.PostRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.home.HomeActivity;
import com.m.k.seetaoism.utils.AppUtils;
import com.m.k.seetaoism.utils.Logger;
import com.m.k.seetaoism.utils.ParamsUtils;
import com.m.k.seetaoism.widgets.CleanEditButton;
import com.m.k.seetaoism.widgets.EditTextButton;
import com.m.k.seetaoism.widgets.TogglePasswordButton;

import java.util.HashMap;


public class PasswordLoginFragment extends MvpBaseFragment<PasswordLoginContract.IPasswordLoginPresenter> implements PasswordLoginContract.IPasswordLoginView {

    private EditText mEdtCount;
    private EditText mEdtPassword;
    private CleanEditButton mBtnCleanAccount;
    private CleanEditButton mBtnCleanPassword;
    private TogglePasswordButton mBtnTogglePassword;

    private TextView mTvCodeLogin;
    private TextView mTvRegister;

    private EditTextButton mBtnLogin;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_password_login;
    }

    @Override
    protected void initView() {

        mEdtCount = findViewById(R.id.auth_password_login_edt_phone_number);
        mEdtPassword = findViewById(R.id.auth_password_login_edt_password);
        mBtnLogin = findViewById(R.id.auth_password_login_btn_login);

        mBtnCleanAccount = findViewById(R.id.auth_password_login_btn_phone_number_clean);
        mBtnCleanPassword = findViewById(R.id.auth_password_login_btn_password_clean);

        mBtnTogglePassword = findViewById(R.id.auth_password_login_btn_toggle_password);


        mTvCodeLogin = findViewById(R.id.auth_password_login_tv_go_code_login);
        mTvRegister = findViewById(R.id.auth_password_login_tv_go_regiester);


        mBtnCleanPassword.bindEditText(mEdtPassword);
        mBtnTogglePassword.bindEditText(mEdtPassword);
        mBtnCleanAccount.bindEditText(mEdtCount);

        mBtnLogin.bindEditText(mEdtPassword);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.login(mEdtCount.getText().toString().trim(),mEdtPassword.getText().toString().trim());
            }
        });



        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvCodeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }




    @Override
    public void onUserResult(MvpResponse<User> response) {

        if(response.isOk()){
            Logger.d("用户登录成功");
        }
    }

    @Override
    public void onInputError(String message) {
            showToast(message);
    }

    @Override
    public void onShowLoading() {
        showPopLoading();
    }

    @Override
    public void onCloseLoading() {
        closeLoading();
    }

    @Override
    public PasswordLoginContract.IPasswordLoginPresenter createPresenter() {
        return new PasswordLoginPresenter();
    }
}
