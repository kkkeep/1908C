package com.m.k.seetaoism.base.p;

import com.m.k.seetaoism.base.v.IBaseSmartView2;
import com.m.k.seetaoism.data.net.request.MvpRequest;

public interface IBaseSmartPresenter2<D1,D2,V extends IBaseSmartView2<D1,D2,?>> extends IBaseSmartPresenter1<D1,V> {
    void setType2(Class<D2> type);
    void doRequest2(MvpRequest<D2> request);

}
