package com.m.k.seetaoism.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecommendData2 {

    private int start; // 普通新闻开始的index ,
    @SerializedName("point_time")
    private long pointTime;  // 视频类新闻 开始index
    private int more; // 是否还有更多的的新闻，
    @SerializedName("flash_id")
    private int flashId;
    private int number; //

    @SerializedName("album_id")
    private String albumId;
    @SerializedName("album_title")
    private String albumTitle;


    @SerializedName("album_list")
    private ArrayList<AlbumNews> albumNews;

    @SerializedName("banner_list")
    private ArrayList<BannerNews> bannerList;

    @SerializedName("flash_list")
    private ArrayList<FlashNews> flashNews;

    @SerializedName("article_list")
    private ArrayList<News> news;


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }



    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getPointTime() {
        return pointTime;
    }

    public void setPointTime(long pointTime) {
        this.pointTime = pointTime;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public int getFlashId() {
        return flashId;
    }

    public void setFlashId(int flashId) {
        this.flashId = flashId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public ArrayList<AlbumNews> getAlbumNews() {
        return albumNews;
    }

    public void setAlbumNews(ArrayList<AlbumNews> albumNews) {
        this.albumNews = albumNews;
    }

    public ArrayList<BannerNews> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<BannerNews> bannerList) {
        this.bannerList = bannerList;
    }

    public ArrayList<FlashNews> getFlashNews() {
        return flashNews;
    }

    public void setFlashNews(ArrayList<FlashNews> flashNews) {
        this.flashNews = flashNews;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }
}
