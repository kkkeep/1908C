package com.m.k.seetaoism.data.net.ok;


import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.entity.User;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 *
 * request;
 * 1. 请求方式  get or post
 * 2. url 每一个 url 不一样
 * 3. 参数：每一个请求的参数不一样
 *
 *
 * response:
 *  返回的数据都是json
 */

public interface ApiService {


    /**
     * 密码登录
     * @param params
     * @return
     */
    @POST(Constrant.URL.LOGIN)
    @FormUrlEncoded
    Observable<String>  loginByPassword(@FieldMap HashMap<String,Object> params );

    /**
     * 密码登录
     * @param params
     * @return
     */
    @PUT("/api/user/login")
    @FormUrlEncoded
    Observable<String>  register(@FieldMap HashMap<String,Object> params );
    /**
     * 密码登录
     * @param params
     * @return
     */
    @POST("/api/user/login")
    @FormUrlEncoded
    Observable<String>  forgetPassword(@FieldMap HashMap<String,Object> params );


    @GET("/api/column/columnmanagelist")
    Observable<HttpResult<ColumnData>> getColumnData(@QueryMap HashMap<String,String> params);




    @POST
    @FormUrlEncoded
    Observable<String> doPost(@Url String url,@HeaderMap HashMap<String,Object> headers, @FieldMap HashMap<String,Object> params);

    @GET
    Observable<String> doGet(@Url String url,@HeaderMap HashMap<String,Object> headers, @QueryMap HashMap<String,Object> params);


}
