package com.m.k.seetaoism.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoData extends NewsData {

    private ArrayList<News> list;

    @Override
    public ArrayList<News> getNewsList() {
        return list;
    }

    @Override
    public void setNewsList(ArrayList<News> list) {
        this.list = list;
    }
}
