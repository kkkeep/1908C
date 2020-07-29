package com.m.k.seetaoism.home;

import android.view.View;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;

public class Fragment1 extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initView() {
        /*findViewById(R.id.fragment1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvpFragmentManager.addOrShowFragment(getFragmentManager(),Fragment2.class,Fragment1.this,R.id.home_fragment_container);
            }
        });*/

        //findViewById(R.id.mvp_loading_gif_view).setVisibility(View.GONE);
        //findViewById(R.id.mvp_loading_group_error).setVisibility(View.VISIBLE);
    }

    @Override
    public int getEnterAnimation() {
        return 0;
    }

    @Override
    public int getPopExitAnimation() {
        return 0;
    }

    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }
}
