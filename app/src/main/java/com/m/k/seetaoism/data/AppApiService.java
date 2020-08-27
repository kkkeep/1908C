package com.m.k.seetaoism.data;


import com.m.k.anotaion.ApiService;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.entity.User;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;


@ApiService
public interface AppApiService  {


    @POST()
    Observable<HttpResult<User>> getUser(@FieldMap HashMap<String,Object> map);
}


