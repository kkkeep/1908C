package com.m.k.seetaoism.base;

public interface IBaseCallBack<T>{

    void onSuccess(T data);
    void onError(String msg);
}
