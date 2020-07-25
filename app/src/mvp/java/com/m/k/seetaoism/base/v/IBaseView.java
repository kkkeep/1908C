package com.m.k.seetaoism.base.v;

import android.content.Context;

import com.m.k.seetaoism.base.p.IBasePresenter;

public interface IBaseView<P extends IBasePresenter>  {
    P createPresenter();
    Context getMvpContent();
}
