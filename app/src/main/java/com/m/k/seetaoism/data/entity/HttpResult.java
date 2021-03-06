package com.m.k.seetaoism.data.entity;


import com.m.k.anotaion.IMvpEntity;
import com.m.k.anotaion.MvpEntity;

@MvpEntity
public class HttpResult<T> implements IMvpEntity {

    /**
     * 'code': '1',
     * 'message': '成功提示',
     * 'data':
     */
    private int code;

    private String message;

    private  T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean isOk() {
        return code == 1 && data != null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
