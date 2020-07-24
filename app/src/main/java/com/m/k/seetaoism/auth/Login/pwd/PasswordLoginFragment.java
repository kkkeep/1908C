package com.m.k.seetaoism.auth.Login.pwd;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.v.MvpBaseFragment;
import com.m.k.seetaoism.data.PostRequest;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.utils.AppUtils;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.HashMap;

import static com.m.k.seetaoism.Constrant.RequestKey.KEY_USER_ACOUNT;
import static com.m.k.seetaoism.Constrant.RequestKey.KEY_USER_PASSWORD;

public class PasswordLoginFragment  extends MvpBaseFragment<PasswordLoginContract.ILoginPresenter> implements PasswordLoginContract.ILoginView{

    private EditText mEdtCount;
    private EditText mEdtPassword;
    private Button mBtnLogin;





    @Override
    protected int getLayoutId() {
        return R.layout.fragment_password_login;
    }

    @Override
    protected void initView() {

        mEdtCount = findViewById(R.id.auth_password_login_edt_count);
        mEdtPassword = findViewById(R.id.auth_password_login_edt_password);
        mBtnLogin =  findViewById(R.id.auth_password_login_btn_login);


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



    }



    @Override
    public void showLoading(){
        Toast.makeText(getActivity(),"显示加载动画",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeLoading() {
        Toast.makeText(getActivity(),"关闭加载动画",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onLoginSuccess(User user) {
        showToast(user.getUser_info().getMy_integral());
    }

    @Override
    public void onLoginFail(String msg) {
       showToast(msg);
    }

    @Override
    public void onRegisterSuccess(User user) {

    }

    @Override
    public void onRegisterFail(User user) {

    }

    @Override
    public void onForgetSuccess(String msg) {

    }

    @Override
    public void onForgetFail(String msg) {

    }

    @Override
    public void onNetError() {
        showToast(R.string.text_error_net);
    }

    @Override
    public void onInputFail(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public PasswordLoginContract.ILoginPresenter createPresenter() {
        return new PasswordLoginPresenter();
    }
}
