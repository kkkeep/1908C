package com.m.k.seetaoism.home;

import android.view.View;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;

public class Fragment3 extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    @Override
    protected void initView() {

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvpFragmentManager.addOrShowFragment(getFragmentManager(),Fragment1.class,Fragment3.this,R.id.home_fragment_container);
            }
        });
    }
}
