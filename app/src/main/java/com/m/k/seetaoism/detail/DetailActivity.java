package com.m.k.seetaoism.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.m.k.mvp.base.BaseActivity;
import com.m.k.mvp.manager.MkPermissionManager;
import com.m.k.mvp.manager.MvpFragmentManager;
import com.m.k.mvp.utils.MvpUtils;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.BaseNews;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.databinding.ActivityDetailBinding;
import com.m.k.systemui.SystemBarConfig;
import com.umeng.socialize.UMShareAPI;

public class DetailActivity extends BaseActivity {


    public static final String KEY_URL = "url";
    public static final String KEY_NEW_ID = "newsId";
    public static final String KEY_NEW_TITLE = "newsTitle";
    public static final String KEY_NEW_IMAGE_URL = "imageUrl";
    public static final String KEY_NEW_DESCRIPTION = "description";
    public static final String KEY_NEW_SHARE_LINK = "shareLink";


    private Bundle paramBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemBarConfig config = new SystemBarConfig(this);

        if(MvpUtils.hasM()){
            config.setStatusBarColor(Color.WHITE);
            config.setStatusBarLightMode(true);
        }else{
            config.setStatusBarColor(Color.GRAY);
        }

        config.apply();

        paramBundle = getIntent().getBundleExtra("bundle");

        MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),DetailFragment.class,null,R.id.detail_fragment_Container,paramBundle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }


    @Override
    protected void initView() {
        /*MkPermissionManager manager = new MkPermissionManager(this);
        manager.checkPermission(this, new MkPermissionManager.OnPermissionCallBack() {
            @Override
            public void onAllMustAccept() {

            }

            @Override
            public void shouldShowRationale(MkPermissionManager.PermissionCall call, String[] unGrantedPermissions) {

            }

            @Override
            public void shouldShowPermissionSetting() {

            }

            @Override
            public void onDenied() {

            }
        },new String []{"android.permission.WRITE_EXTERNAL_STORAGE"},null);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public static void startDetailActivity(Context context, BaseNews news){
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(DetailActivity.KEY_URL,news.getLink());
        bundle.putString(DetailActivity.KEY_NEW_ID,news.getId());
        bundle.putString(DetailActivity.KEY_NEW_TITLE,news.getTheme());
        bundle.putString(DetailActivity.KEY_NEW_IMAGE_URL,news.getImageUrl());
        bundle.putString(DetailActivity.KEY_NEW_DESCRIPTION,news.getDescription());
        bundle.putString(DetailActivity.KEY_NEW_SHARE_LINK,news.getShareLink());

        intent.putExtra("bundle",bundle);
        context.startActivity(intent);




    }
}
