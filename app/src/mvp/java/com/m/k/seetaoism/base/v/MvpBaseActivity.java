package com.m.k.seetaoism.base.v;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.m.k.seetaoism.base.BaseActivity;
import com.m.k.seetaoism.base.BaseView;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.widgets.MvpLoadingView;

public abstract class MvpBaseActivity<P extends IBasePresenter> extends BaseActivity implements BaseView,IBaseView<P> {

    private MvpLoadingView mLoadingView;

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         mPresenter = createPresenter();
         mPresenter.bindView(this);

         loadData();
    }

/*
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(mPresenter != null){
                if(mPresenter.cancelRequest()){
                    closeLoading();
                    return true;
                }
            }

        }

        return super.onKeyUp(keyCode, event);

    }*/

    protected abstract void loadData();

    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mLoadingView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBind();
    }
}
