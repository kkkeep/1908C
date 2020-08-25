package com.m.k.seetaoism.home;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.m.k.mvp.widgets.BottomNavigation;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseActivity;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.seetaoism.data.repository.RecommendNewsRepository;
import com.m.k.seetaoism.databinding.ActivityHomeBinding;
import com.m.k.seetaoism.home.recommend.RecommendFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;
import com.m.k.seetaoism.utils.Logger;
import com.m.k.systemui.SystemBarConfig;

import java.util.HashMap;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemBarConfig config = new SystemBarConfig(this);
        config.setStatusBarLightMode(true);
        config.setStatusBarColor(Color.WHITE).apply();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }




    @Override
    protected void initView() {


        binding.bottomNavigation.addItem(R.drawable.tab_recommend_selector,getString(R.string.text_tab_recommend))
                .addItem(R.drawable.tab_video_selector,getString(R.string.text_tab_video))
                .addItem(R.drawable.tab_special_selector,getString(R.string.text_tab_special))
                .addItem(R.drawable.tab_mine_selector,getString(R.string.text_tab_mine))
                .apply();

        binding.bottomNavigation.setTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelect(View tab, int position) {
                MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(), RecommendFragment.class,null,R.id.home_fragmentContainer);
            }

            @Override
            public void onTabUnSelect(View tab, int position) {

            }

            @Override
            public void onTabReSelected(View tab, int position) {

            }
        });


       // binding.drawerLayout.openDrawer(Gravity.LEFT);



    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d();
}

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.d("++++++++------");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.d("++++++++");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RecommendNewsRepository.destroy();
    }

    @Override
    protected void bindingView(View view) {
        binding  = ActivityHomeBinding.bind(view);
    }
}
