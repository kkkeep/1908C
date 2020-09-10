package com.m.k.seetaoism.data.interceptors;

import com.m.k.anotaion.OkInterceptor;
import com.m.k.mvp.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

@OkInterceptor(1)
public class Intercepter1 implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Logger.d(" 自己定义拦截器1 ");
        return chain.proceed(chain.request());
    }
}
