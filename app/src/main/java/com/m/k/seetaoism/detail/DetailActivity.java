package com.m.k.seetaoism.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.m.k.mvp.base.BaseActivity;
import com.m.k.mvp.manager.MvpFragmentManager;
import com.m.k.mvp.utils.MvpUtils;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.ActivityDetailBinding;
import com.m.k.systemui.SystemBarConfig;

public class DetailActivity extends BaseActivity {

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

    }
}
