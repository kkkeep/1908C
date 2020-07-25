package com.m.k.seetaoism.base.v;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.seetaoism.base.p.BaseSmartPresenter1;
import com.m.k.seetaoism.data.net.request.MvpRequest;

import java.lang.reflect.ParameterizedType;

public abstract class BaseSmartFragment1<D> extends MvpBaseFragment<BaseSmartPresenter1<D,?>> implements IBaseSmartView1<D, BaseSmartPresenter1<D,?>> {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<D> aClass = (Class<D>) superClass.getActualTypeArguments()[0];
        mPresenter.setType(aClass);

    }

    public void doRequest(MvpRequest<D> request) {
        mPresenter.doRequest(request);
    }

    @Override
    public BaseSmartPresenter1<D,?> createPresenter() {
        return new BaseSmartPresenter1<>();
    }

}
