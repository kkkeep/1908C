package com.m.k.seetaoism.base.p;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.m.k.seetaoism.base.IBaseMode;
import com.m.k.seetaoism.base.v.IBaseView;

public interface IBasePresenter<V extends IBaseView> {
    void bindView(V view);
    void unBind();

    Context getMvpContent();

}
