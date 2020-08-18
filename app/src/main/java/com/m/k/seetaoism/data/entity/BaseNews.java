package com.m.k.seetaoism.data.entity;

import com.google.gson.annotations.SerializedName;

public class BaseNews {

    /**
     *  {
     *                 "id":"35408",
     *                 "theme":"中国铁建集团董事长陈奋健坠楼身亡，年仅58岁",
     *                 "description":"8月18日，中国铁建股份有限公司董事会发布公告称，沉重宣布，董事长、执行董事、58岁中国铁道建筑集团董事长陈奋健意外去世",
     *                 "image_url":"https://s.seetao.com/Public/Uploads/thumbnail/2020-08-18/y_wwpujk4qemi03.png",
     *                 "is_good":0,
     *                 "is_collect":0,
     *                 "is_comment":"1",
     *                 "link":"https://www.seetao.com/app_details/35408/zh/ios/version/2.html",
     *                 "share_link":"https://www.seetao.com/m_details/35408/zh.html"
     *             }
     */

    protected String id;
    protected String theme;
    protected String description;
    @SerializedName("image_url")
    protected String imageUrl;
    @SerializedName("is_good")
    protected int isGood;
    @SerializedName("is_collect")
    protected int isCollect;
    protected String link;

    @SerializedName("is_comment")
    protected String isComment;
    @SerializedName("share_link")
    protected String shareLink;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIsGood() {
        return isGood;
    }

    public void setIsGood(int isGood) {
        this.isGood = isGood;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }
}
