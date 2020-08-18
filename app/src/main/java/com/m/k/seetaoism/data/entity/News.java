package com.m.k.seetaoism.data.entity;

public class News extends BaseNews {


    private int type; //'文章类型：1新闻，2快讯，3图片，4视频，5期刊，6专题',
    private String column_name;
    private String content;
    private String edit_time;
    private String lead_one;
    private String video_is_sans_href;
    private String video_url;
    private int view_type; //'视图类型：1左图，2中间大图，3右图，4视频，5即时',


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getLead_one() {
        return lead_one;
    }

    public void setLead_one(String lead_one) {
        this.lead_one = lead_one;
    }

    public String getVideo_is_sans_href() {
        return video_is_sans_href;
    }

    public void setVideo_is_sans_href(String video_is_sans_href) {
        this.video_is_sans_href = video_is_sans_href;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getView_type() {
        return view_type;
    }

    public void setView_type(int view_type) {
        this.view_type = view_type;
    }
}
