package com.m.k.mvp.manager;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public class MvpManager {


    private static  Context mApplication;

    private static  UiHandler mUiHandler;

    public static void init(Context context){
        if(Looper.getMainLooper().getThread() != Thread.currentThread()){
            throw new IllegalThreadStateException("Mvp.init(context) 方法 必须调用在主线程进行初始化，不用担心，Mvp 的具体初始化操作不会占用主线程");
        }
        mUiHandler = new UiHandler();
        mApplication = context;
    }


     public static Context getContext(){
        if(mApplication == null){
            throw new NullPointerException("Mvp 框架还没有初始化，请初始化后在使用");
        }
        return mApplication;
    }


     static  void postUI(Runnable runnable){
        mUiHandler.post(runnable);
    }


     static  class UiHandler extends Handler{}
}
