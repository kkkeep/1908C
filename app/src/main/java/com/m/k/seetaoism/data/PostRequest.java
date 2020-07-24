package com.m.k.seetaoism.data;

import com.m.k.seetaoism.data.net.MvpRequest;

public class PostRequest extends MvpRequest {
    public PostRequest(String url) {
        super(url);
    }

    public PostRequest() {
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }
}
