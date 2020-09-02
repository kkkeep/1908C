package com.m.k.mvp.base.p;

import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.mvp.base.v.IBaseSmartView1;

import java.lang.reflect.Type;

public interface IBaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends IBasePresenter<V>{
    void setType(Type type);
    void doRequest(MvpRequest<D> request);



}
