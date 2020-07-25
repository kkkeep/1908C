package com.m.k.seetaoism.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.m.k.seetaoism.R;


public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(getLayoutId(),container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();


    protected <T extends View> T findViewById(@IdRes int id){
        return getView().findViewById(id);
    }


    protected void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id){
        Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
    }
}
