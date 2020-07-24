package com.m.k.seetaoism.base;

import com.m.k.seetaoism.data.net.MvpRequest;

public interface IBaseMode {

    <T> void doRequest(MvpRequest request, IBaseCallBack<T> callBack);
}
