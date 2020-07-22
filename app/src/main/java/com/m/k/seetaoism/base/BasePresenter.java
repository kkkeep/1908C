package com.m.k.seetaoism.base;

import com.m.k.seetaoism.data.entity.HttpResult;

public abstract class BasePresenter<T> {


    public void handSuccess(HttpResult<T> result) {
        if (result.getCode() == 1) {
            if (result.getData() != null) {
                onSuccess(result.getData());
            } else {
                    onFail("服务器异常，时候重试");
            }
        } else {
             onFail(result.getMessage());
        }
    }




    public abstract void onSuccess(T data);
    public abstract void onFail(String msg);
}
