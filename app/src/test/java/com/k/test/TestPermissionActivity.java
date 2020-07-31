package com.k.test;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseActivity;
import com.m.k.seetaoism.manager.MkPermissionManager;
import com.m.k.seetaoism.utils.Logger;

import java.util.ArrayList;

/**
 * 1. 第一次点击按钮：判断是否有相应的权限，
 * 有：直接调用相应代码（打开相机）
 * 没有：请求授权
 * 如果用户同意：在回调里面直接调用相应代码（打开相机）
 * 如果用户拒绝：
 * 如果用户再次点击按钮：不要直接请求授权
 * 弹出一个自己写的pop or dialog 向用户解释为什么需要这个权限。
 * 如果用户看明白了，点击了同意：
 * 请求授权再次弹出系统授权对话框
 * 如果用户同意：在回调里面直接调用相应代码（打开相机）
 * 如果用户拒绝了并且点击了不在提示：
 * 用户在此点击按钮请求授权：系统会直接回调授权失败，不会弹出任何授权对话框
 * <p>
 * <p>
 * 如果用户仍然掉了决绝：
 * <p>
 * <p>
 * <p>
 * <p>
 * 注意：授权对话框的不在提示，只要在第一次拒绝授权后，第二次请求授权时才会出现
 */
public class TestPermissionActivity extends BaseActivity {

    Button mRequestPermission;
    private static final String allNeedPermissions[] = new String[]{Manifest.permission.CAMERA
    ,Manifest.permission.READ_PHONE_NUMBERS,Manifest.permission.ACCESS_FINE_LOCATION};

    private  static  String optionalPermissions [] = new String[]{Manifest.permission.BODY_SENSORS}; //  这个权限是可选的

    private ArrayList<String> unGrantedPermissions = new ArrayList<>();

    private ArrayList<String> needToSettingPermissions = new ArrayList<>(); //  未授权那一部分那种，勾选了不再提示的那一部分

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_permission;
    }


    @Override
    protected void initView() {
        mRequestPermission = findViewById(R.id.test_btn_permission_request);

        mRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unGrantedPermissions.clear();
                needToSettingPermissions.clear();
                if (isAllGranted(allNeedPermissions)) {
                    showToast("有权限，可以拍照");
                } else {


                    boolean isShouldShowDialog = isShouldShowPermissionRationale(unGrantedPermissions);

                    // 第一次调用这个方法（还没有请求授权），返回 false.
                    // 第一次以后再次调用这个（之前发生过请求授权，被用户拒绝了,但是没有勾选不再提示，如果勾选了，return false），return true. 意思就是告诉你，需要向用户解释为什么需要这个权限


                    Logger.d("isShouldShowDialog = %s", isShouldShowDialog);
                    if (isShouldShowDialog) { //  需要显示解释对话框
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("上传头像需要相机权限，否则没法实现").setNegativeButton("决绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                showToast("权限不足");
                            }
                        }).setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Logger.d("用户看了解释对话框，同意授权");
                                ActivityCompat.requestPermissions(TestPermissionActivity.this,  unGrantedPermissions.toArray(new String[unGrantedPermissions.size()]), 100);
                            }
                        });
                        builder.show();
                    } else {
                        Logger.d("不需要解释对话框，直接请求授权");
                        ActivityCompat.requestPermissions(TestPermissionActivity.this, unGrantedPermissions.toArray(new String[unGrantedPermissions.size()]), 100);

                    }
                }
            }
        });



        findViewById(R.id.test_btn_permission_requet2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MkPermissionManager(TestPermissionActivity.this).checkPermission(TestPermissionActivity.this, new MkPermissionManager.OnPermissionCallBack() {
                    @Override
                    public void onAllMustAccept() {
                            showToast("所有必须权限都接受了，可以进行下一步操作");
                    }

                    @Override
                    public void shouldShowRationale(MkPermissionManager.PermissionCall call,String unGrantedPermissions []) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("做什么，需要权限")
                                .setNegativeButton("决绝", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        for(int i = 0; i < unGrantedPermissions.length;i++){
                                            for(int j = 0; j < allNeedPermissions.length; j++){
                                                if(unGrantedPermissions[i] == allNeedPermissions[j]){
                                                    showToast("权限不足");
                                                    return;
                                                }
                                            }
                                        }

                                        showToast("所有必须权限都接受了，可以进行下一步操作");
                                    }
                                }).setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                call.requestPermission(); // 重新请求权限
                            }
                        }).show();
                    }

                    @Override
                    public void shouldShowPermissionSetting() {
                      showSettingDialog(); // 给用解释，需要到设置页面去手动打开权限
                    }

                    @Override
                    public void onDenied() {
                        showToast("权限不足");
                    }
                },allNeedPermissions,optionalPermissions);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {

            ArrayList<String> deniedList = new ArrayList<>();

            for(int i = 0; i < grantResults.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    deniedList.add(permissions[i]);
                }
            }

            if(deniedList.size() == 0){// 如果全部授权
                showToast("全部授权");
            }else{
                for(String permission : deniedList){
                   if(!ActivityCompat.shouldShowRequestPermissionRationale(TestPermissionActivity.this, permission)){
                       showSettingDialog();
                       return;
                   }
                }

                showToast("权限不足");

            }


        }
    }*/




    public void showSettingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("上传头像需要相机权限，否则没法实现，点击同意按钮进入设置页面打开权限，否则点击拒绝").setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               showToast("权限不足");
            }
        }).setPositiveButton("同意", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Logger.d("用户同意到设置页面打开权限");
                toSelfSetting(TestPermissionActivity.this);
            }
        });
        builder.show();
    }

    public boolean isAllGranted(String[] all) {


        for (int i = 0; i < all.length; i++) {
            if (ActivityCompat.checkSelfPermission(this, all[i]) != PackageManager.PERMISSION_GRANTED) { // 没有这个权限
                unGrantedPermissions.add(all[i]);
            }
        }

        if (unGrantedPermissions.size() > 0) {
            return false;
        }
        return true;
    }


    public boolean isShouldShowPermissionRationale(ArrayList<String> permissions) {
        for (String permission : permissions) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(TestPermissionActivity.this, permission)) {
               needToSettingPermissions.add(permission);
            }
        }

        if(needToSettingPermissions.size() > 0){ // 有点了不在询问的权限
            if(needToSettingPermissions.size() == permissions.size()){ // 所有未授权的全部点了不在询问
                return false;
            }
            return true; // 只有有一部分需要显示的，，那么还是显示一下
        }
        return true; // 所有未授权的权限中，都没有点击不在询问。所以也需要
    }

    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }


}
