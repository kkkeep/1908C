package com.m.k.umeng.share;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.BuildConfig;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.bean.PlatformName;

/*
 * created by Cherry on 2019-11-27
 **/
public class ShareUtils {


    /**
     *
     * @param context
     * @param channel 渠道号
     * @param isDebug 是否打开debug 日子
     */


    public static void init(Context context,String channel,boolean isDebug){

        //UMConfigure.setLogEnabled(true);

        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_MANUAL);
        //平台表单名称
        PlatformName.SINA="新浪微博";
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */




        //第一个参数表示 AppID ，第二：AppSecret
        PlatformConfig.setWeixin("wxc18a6ee8aede4929", "cc1459aa71cc4c3ea24232d62df6231d"); //

        //第一个参数表示 APP ID ，第二：APP KEY
        PlatformConfig.setQQZone("1109759123", "a3x6lGx32PYMrmK3");
        PlatformConfig.setQQFileProvider("com.m.k.umeng.share.fileProvider");

        // 第一个参数表示 APP ID ，第二：App Secret，第三： 回调 地址
        PlatformConfig.setSinaWeibo("110298133","afb59986db07d0b9b0886e9980d28d1c","https://www.seetao.com");

    }









}
