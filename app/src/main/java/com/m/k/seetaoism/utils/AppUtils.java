package com.m.k.seetaoism.utils;

import android.text.TextUtils;

public class AppUtils {


    public static boolean isValidUserCount(String count){
        if(!TextUtils.isEmpty(count)){
            if(count.length() == 11){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUserPasssword(String password){
        if(!TextUtils.isEmpty(password)){
            if(password.length() >= 6){
                return true;
            }
        }
        return false;
    }
}
