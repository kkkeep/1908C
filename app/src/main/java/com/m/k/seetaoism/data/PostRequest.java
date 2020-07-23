package com.m.k.seetaoism.data;

import com.m.k.seetaoism.data.net.MvpRequest;

public class PostRequest extends MvpRequest {
    public PostRequest(String url) {
        super(url);
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }
}
