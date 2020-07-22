package com.m.k.seetaoism.data.net.ok;


import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.entity.User;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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
    @POST("/api/user/login")
    @FormUrlEncoded
    Observable<HttpResult<User>>  loginByPassword(@FieldMap HashMap<String,String> params );



    @GET("/api/column/columnmanagelist")
    Observable<HttpResult<ColumnData>> getColumnData(@QueryMap HashMap<String,String> params);



   /* @POST
    @FormUrlEncoded
    Observable<String> doPost(@Url String url, @FieldMap HashMap<String,String> params);

    @GET
    Observable<String> doGet(@Url String url, @QueryMap HashMap<String,String> params);*/


}
