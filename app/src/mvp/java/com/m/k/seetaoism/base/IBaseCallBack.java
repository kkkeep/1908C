package com.m.k.seetaoism.base;

import com.m.k.seetaoism.data.net.response.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public interface IBaseCallBack<T>{

    void onResult(MvpResponse<T> response);


    default void onStart(Disposable disposable){

    }

}
