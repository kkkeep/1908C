package com.m.k.seetaoism.auth.Login.pwd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.utils.AppUtils;

public class PasswordLoginFragment  extends Fragment implements PasswordLoginContract.ILoginView{

    private EditText mEdtCount;
    private EditText mEdtPassword;
    private Button mBtnLogin;


    private PasswordLoginContract.ILoginPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new PasswordLoginPresenter();
        mPresenter.bindView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_password_login,container,false);


        mEdtCount = root.findViewById(R.id.auth_password_login_edt_count);
        mEdtPassword = root.findViewById(R.id.auth_password_login_edt_password);
        mBtnLogin = root.findViewById(R.id.auth_password_login_btn_login);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login();
            }
        });

        return root;
    }



    private void login(){

        String count = mEdtCount.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        mPresenter.login(count,password);

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
        Toast.makeText(getActivity(),user.getUser_info().getNickname(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFail(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetError() {
        Toast.makeText(getActivity(),"没有网络，请检查网络",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInputFail(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
