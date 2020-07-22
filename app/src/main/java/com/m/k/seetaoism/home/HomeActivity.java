package com.m.k.seetaoism.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m.k.Test;
import com.m.k.seetaoism.data.entity.User;


public class HomeActivity extends AppCompatActivity {


    public static final String KEY_FROM = "from_1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Test.main();


    }


    // 第一 成功： 给我 data 数据
    // 第二：失败： 给我错误信息

    public void onUserSuccess(User result){
       // 拿到user 对象做用户数据显示
    }

    public void onUserError(String msg){
       // 显示错误信息
    }
}
