package com.m.k.seetaoism.base.p;

import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.seetaoism.base.m.IBaseMode;
import com.m.k.seetaoism.base.v.IBaseSmartView1;

public interface IBaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends IBasePresenter<V>{
    void setType(Class<D> type);
    void doRequest(MvpRequest<D> request);



}
