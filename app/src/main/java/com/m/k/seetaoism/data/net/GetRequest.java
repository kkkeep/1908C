package com.m.k.seetaoism.data.net;

import com.m.k.seetaoism.data.net.MvpRequest;

import java.util.HashMap;

public class GetRequest extends MvpRequest {


    public GetRequest(String url) {
        super(url);
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }
}
