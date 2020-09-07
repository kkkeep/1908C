package com.m.k.seetaoism.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.MvpBaseFragment;
import com.m.k.mvp.manager.MvpFragmentManager;
import com.m.k.seetaoism.R;

public class DetailFragment extends MvpBaseFragment {
    @Override
    protected int getLayoutId() {

        return R.layout.fragment_detail;
    }

    @Override
    public IBasePresenter createPresenter() {
        return null;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    protected void initView() {
        super.initView();

        MvpFragmentManager.addOrShowFragment(getChildFragmentManager(),DetailContentFragment.class,null,R.id.detail_content_fragment_Container,getArguments());
    }

    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }
}
