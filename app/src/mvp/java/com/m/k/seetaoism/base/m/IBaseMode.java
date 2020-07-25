package com.m.k.seetaoism.base.m;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.net.request.MvpRequest;

public interface IBaseMode {

    <T> void doRequest(MvpRequest<T> request, IBaseCallBack<T> callBack);
}
