package com.m.k.seetaoism.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.base.v.MvpBaseActivity;
import com.m.k.seetaoism.base.v.MvpBaseFragment;
import com.m.k.seetaoism.widgets.MvpLoadingView;

public interface BaseView {


    default void showPopLoading() {
        showLoading(MvpLoadingView.MODE_POP,false);
    }

    default void showPopLoading(boolean isEnableBackCancel) {

        showLoading(MvpLoadingView.MODE_POP,isEnableBackCancel);
    }
    default void showFullLoading() {
        showLoading(MvpLoadingView.MODE_FULL,false);
    }
    default void showFullLoading(boolean isEnableBackCancel) {
        showLoading(MvpLoadingView.MODE_FULL,isEnableBackCancel);
    }
    default void closeLoading() {
        getLoadingView().closeLoading();
    }

    default void onError() {
        getLoadingView().onError();
    }
    default void onError(MvpLoadingView.OnRetryCallBack onRetryCallBack) {
        getLoadingView().onError(onRetryCallBack);
    }
    default void onError(String message, MvpLoadingView.OnRetryCallBack callBack) {
        getLoadingView().onError(message,callBack);
    }

    default void showLoading(@MvpLoadingView.LoadingMode int mode,boolean isEnableBackCancel) {
        MvpLoadingView loadingView = MvpLoadingView.inject(getDefaultLoadingParent());
        loadingView.setEnableBackCancel(isEnableBackCancel);

        if(isEnableBackCancel){
            loadingView.setCancelCallBack(new MvpLoadingView.OnCancelCallBack() {
                @Override
                public void onCancel() {
                    if(getPresenter() != null){
                        getPresenter().cancelRequest();
                    }

                }
            });
        }
        setLoadView(loadingView);
        loadingView.showLoading(mode);
    }


    default ViewGroup getDefaultLoadingParent() {
        if (this instanceof MvpBaseActivity) {
            return (ViewGroup) findViewById(android.R.id.content);
        } else if (this instanceof MvpBaseFragment) {
            return (ViewGroup) ((MvpBaseFragment) this).getView();
        }
        return null;
    }

    <T extends View> T findViewById(@IdRes int id);


    void setLoadView(MvpLoadingView loadView);

    MvpLoadingView getLoadingView();

   IBasePresenter getPresenter();

}
