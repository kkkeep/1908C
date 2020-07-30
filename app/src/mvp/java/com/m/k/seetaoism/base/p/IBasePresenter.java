package com.m.k.seetaoism.base.p;

import android.content.Context;

import com.m.k.seetaoism.base.v.IBaseView;

public interface IBasePresenter<V extends IBaseView> {
    void bindView(V view);
    void unBind();
    Context getMvpContent();
    boolean cancelRequest();

}
