package com.m.k.seetaoism.data.entity;

import com.m.k.banner.IBannerData;

public class NewsBanner implements IBannerData {


    private String url;
    private String title;


    public NewsBanner(String title) {
        this.title = title;
    }

    @Override
    public String getImageUrl() {
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
