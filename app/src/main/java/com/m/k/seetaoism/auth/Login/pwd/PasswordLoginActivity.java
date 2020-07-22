package com.m.k.seetaoism.auth.Login.pwd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.utils.AppUtils;

public class PasswordLoginActivity extends AppCompatActivity implements PasswordLoginContract.ILoginView {

    private EditText mEdtCount;
    private EditText mEdtPassword;
    private Button mBtnLogin;
    private PasswordLoginContract.ILoginPresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_password_login);


        mPresenter = new PasswordLoginPresenter();

        mPresenter.bindView(this);

        mEdtCount = findViewById(R.id.auth_password_login_edt_count);
        mEdtPassword = findViewById(R.id.auth_password_login_edt_password);
        mBtnLogin = findViewById(R.id.auth_password_login_btn_login);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){

        String count = mEdtCount.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        mPresenter.login(count,password);

    }

    @Override
    public void onLoginSuccess(User user) {
        Toast.makeText(this,user.getUser_info().getNickname(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetError() {
        Toast.makeText(this,"没有网络，请检查网络",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onInputFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"显示加载动画",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeLoading() {
        Toast.makeText(this,"关闭加载动画",Toast.LENGTH_SHORT).show();
    }
}
