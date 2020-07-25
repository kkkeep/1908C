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
        findViewById(R.id.fragment1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvpFragmentManager.addOrShowFragment(getFragmentManager(),Fragment2.class,null,R.id.home_fragment_container);
            }
        });
    }
}
