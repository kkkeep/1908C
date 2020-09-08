package com.m.k;


import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.m.k.anotaion.Umeng;
import com.m.k.seetaoism.BuildConfig;
import com.m.k.umeng.share.ShareUtils;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.utils.ParamsUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;



public class JDApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        MvpManager.setParamsGetter(ParamsUtils::getCommonParams);

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

        initUmengShare();


   /*     SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {//设置全局的Header构建器
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new ClassicsHeader(context)
                        .setDrawableSize(14)
                        .setTextSizeTitle(12)
                        .setAccentColor(0xffb5b5b5)//文字提示颜色
                        .setPrimaryColor(0xfffafafa)//全局设置主题颜色
                        .setEnableLastTime(false);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() { //设置全局的Footer构建器
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context) //指定为经典Footer，默认是 BallPulseFooter
                        .setDrawableSize(14)
                        .setTextSizeTitle(12)
                        .setAccentColor(0xffb5b5b5)//文字提示颜色
                        .setPrimaryColor(0xfffafafa);//全局设置主题颜色
            }
        });*/

     /*   AutoSizeConfig.getInstance().setOnAdaptListener(new onAdaptListener() {
            @Override
            public void onAdaptBefore(Object target, Activity activity) {
                //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
                int w = ScreenUtils.getScreenSize(activity)[0];
                int h = ScreenUtils.getScreenSize(activity)[1];

                int sw =  Math.min(w,h);
                int sh = Math.max(w,h);

               AutoSizeConfig.getInstance().setScreenWidth(sw);
               AutoSizeConfig.getInstance().setScreenHeight(sh);

            }

            @Override
            public void onAdaptAfter(Object target, Activity activity) {

            }
        });*/

    }
    @Umeng(BuildConfig.APPLICATION_ID)
    public void initUmengShare(){

        ShareUtils.init(this,"chanel",BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
