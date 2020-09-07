package com.m.k.seetaoism.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RelatedNewsData {


    @SerializedName("access_article_list")
    private ArrayList<News> newsList;


    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }
}
