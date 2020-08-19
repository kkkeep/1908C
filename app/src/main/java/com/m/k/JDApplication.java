package com.m.k;


import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.m.k.mvp.MvpConfig;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.utils.ParamsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

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


        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return (RefreshHeader) LayoutInflater.from(context).inflate(R.layout.layout_refresh_header,null,false);
            }

        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return (RefreshFooter) LayoutInflater.from(context).inflate(R.layout.layout_refresh_footer,null,false);
            }
        });
    }
}
