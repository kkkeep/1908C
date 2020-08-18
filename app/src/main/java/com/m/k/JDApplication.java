package com.m.k;


import android.app.Application;

import com.m.k.mvp.MvpConfig;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.HashMap;

public class JDApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        MvpConfig mvpConfig = new MvpConfig(this, new MvpConfig.ParamsGetter() {
            @Override
            public HashMap<String, Object> getParams() {
                return ParamsUtils.getCommonParams();
            }

        });
        MvpManager.init(mvpConfig);


    }
}
