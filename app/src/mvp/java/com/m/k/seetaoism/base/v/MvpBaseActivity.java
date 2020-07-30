package com.m.k.seetaoism.base.v;

import android.view.View;

import com.m.k.seetaoism.widgets.MvpLoadingView;

public class MvpBaseActivity extends BaseActivity implements BaseView {

    private MvpLoadingView mLoadingView;


    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mLoadingView;
    }
}
