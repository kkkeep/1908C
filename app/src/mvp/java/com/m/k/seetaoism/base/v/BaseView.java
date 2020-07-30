package com.m.k.seetaoism.base.v;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.widgets.MvpLoadingView;

public interface BaseView {


    default void showPopLoading(){
        showLoading(MvpLoadingView.MODE_POP);
    }

    default void showFullLoading(){
       showLoading(MvpLoadingView.MODE_FULL);
    }

   default void closeLoading(){
        getLoadingView().closeLoading();
   }


   default void onError(){
        getLoadingView().onError();
   }

    default void showLoading(@MvpLoadingView.LoadingMode int  mode){


           MvpLoadingView loadingView =   MvpLoadingView.inject(getDefaultLoadingParent());
           setLoadView(loadingView);
           loadingView.showLoading(mode);


        //loadingView.showLoading(MvpLoadingView.MODE_POP);

    }


    default ViewGroup getDefaultLoadingParent(){
       if(this instanceof MvpBaseActivity){
            return (ViewGroup) findViewById(android.R.id.content);
       }else if(this instanceof MvpBaseFragment){
          return (ViewGroup) ((MvpBaseFragment)this).getView();
       }
       return null;
    }

    <T extends View> T findViewById(@IdRes int id);


    void setLoadView(MvpLoadingView loadView);

    MvpLoadingView getLoadingView();

}
