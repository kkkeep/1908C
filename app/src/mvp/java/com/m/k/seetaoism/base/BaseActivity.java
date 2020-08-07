package com.m.k.seetaoism.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;


public abstract class BaseActivity extends RxAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayoutId() > 0){
            setContentView(getLayoutId());
            initView();
        }
    }

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }


    protected abstract  int getLayoutId();

    protected abstract void initView();


}
