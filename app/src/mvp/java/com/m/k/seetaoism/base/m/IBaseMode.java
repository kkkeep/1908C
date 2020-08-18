package com.m.k.seetaoism.base.m;

import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.trello.rxlifecycle4.LifecycleProvider;


public interface IBaseMode {

    <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack);
}
