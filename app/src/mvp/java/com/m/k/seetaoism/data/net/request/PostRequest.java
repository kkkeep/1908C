package com.m.k.seetaoism.data.net.request;

public class PostRequest<T> extends MvpRequest<T> {
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
