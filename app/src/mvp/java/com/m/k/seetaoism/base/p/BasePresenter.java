package com.m.k.seetaoism.base.p;

import android.content.Context;

import com.m.k.seetaoism.base.v.IBaseView;
import com.trello.rxlifecycle4.LifecycleProvider;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{

    protected V mView;


    @Override
    public void bindView(V view) {
        mView = view;
    }

    @Override
    public void unBind() {
        mView = null;
    }

    @Override
    public Context getMvpContent() {
        if(mView != null){
            return mView.getMvpContent();
        }
       return null;
    }

    public LifecycleProvider getLifecycleProvider(){
        return (LifecycleProvider) mView;
    }
}
