package com.m.k.seetaoism.base;

import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.response.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public class NoResultCallBack<T> implements IBaseCallBack<T> {
    @Override
    public void onResult(MvpResponse<T> response) {

    }

    @Override
    public void onStart(Disposable disposable) {

    }
}
