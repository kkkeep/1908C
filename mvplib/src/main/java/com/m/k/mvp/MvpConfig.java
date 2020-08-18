package com.m.k.mvp;

import android.content.Context;

import java.util.HashMap;

public class MvpConfig {

    private Context context;

    private ParamsGetter paramsGetter;



    public MvpConfig(Context context) {
        this.context = context.getApplicationContext();
    }

    public MvpConfig(Context context, ParamsGetter paramsGetter) {
        this.context = context;
        this.paramsGetter = paramsGetter;
    }



    public static interface ParamsGetter{

        HashMap<String,Object> getParams();
        default HashMap<String,Object> getHeaders(){
           return new HashMap<>();
       }

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ParamsGetter getParamsGetter() {
        return paramsGetter;
    }

    public void setParamsGetter(ParamsGetter paramsGetter) {
        this.paramsGetter = paramsGetter;
    }
}
