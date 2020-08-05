package com.m.k.seetaoism.base.p;

import com.m.k.seetaoism.base.m.IBaseMode;
import com.m.k.seetaoism.base.v.IBaseSmartView1;
import com.m.k.seetaoism.data.net.request.MvpRequest;

public interface IBaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends IBasePresenter<V>{
    void setType(Class<D> type);
    void doRequest(MvpRequest<D> request);



}
