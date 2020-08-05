package com.m.k;


import android.app.Application;

import com.m.k.mvp.manager.MvpManager;
import com.m.k.seetaoism.data.entity.User;

public class JDApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        MvpManager.init(this);
    }
}
