package com.m.k.mvp.base.v;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.mvp.base.p.BaseSmartPresenter3;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseSmartFragment3<D1,D2,D3> extends MvpBaseFragment<BaseSmartPresenter3<D1,D2,D3,?>> implements IBaseSmartView3<D1,D2,D3, BaseSmartPresenter3<D1,D2,D3,?>>{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();

        Type aClass =  superClass.getActualTypeArguments()[0];
        Type aClass2 =  superClass.getActualTypeArguments()[1];
        Type aClass3 =  superClass.getActualTypeArguments()[2];

        mPresenter.setType(aClass);
        mPresenter.setType2(aClass2);
        mPresenter.setType3(aClass3);
    }

    protected void doRequest1(MvpRequest<D1> request){
        mPresenter.doRequest(request);
    }

    protected void doRequest2(MvpRequest<D2> request){
        mPresenter.doRequest2(request);
    }

    protected void doRequest3(MvpRequest<D3> request){
        mPresenter.doRequest3(request);
    }

    @Override
    public BaseSmartPresenter3<D1, D2,D3, ?> createPresenter() {
        return new BaseSmartPresenter3<>();
    }
}
