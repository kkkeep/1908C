package com.m.k.seetaoism.base.v;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.base.BaseView;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.widgets.MvpLoadingView;

public abstract class MvpBaseFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> , BaseView {

    protected P mPresenter;

    protected MvpLoadingView mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.bindView(this);


    }


    @Override
    public Context getMvpContent() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unBind();
    }

    @Override
    public IBasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mLoadingView;
    }



}
