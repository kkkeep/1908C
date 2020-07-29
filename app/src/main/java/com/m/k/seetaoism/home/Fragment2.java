package com.m.k.seetaoism.home;

import android.view.View;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;

public class Fragment2 extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_2;
    }

    @Override
    protected void initView() {
        findViewById(R.id.fragment2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvpFragmentManager.addOrShowFragment(getFragmentManager(),Fragment3.class,Fragment2.this,R.id.home_fragment_container);
            }
        });
    }


    @Override
    public Action getActionFroPreFragment() {
        return Action.NONE;

    }
}
