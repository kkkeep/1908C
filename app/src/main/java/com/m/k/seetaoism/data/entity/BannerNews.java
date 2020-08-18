package com.m.k.seetaoism.data.entity;

import com.m.k.banner.IBannerData;

public class BannerNews extends BaseNews implements IBannerData {
    @Override
    public String getTitle() {
        return getTheme();
    }
}
