package com.m.k.seetaoism.base.v;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.seetaoism.base.IBaseMode;
import com.m.k.seetaoism.base.p.IBasePresenter;

public abstract class MvpBaseFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> {

    protected P mPresenter;


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
}
