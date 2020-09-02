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

    private long currentTime;


    private Ad ad;

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

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


    public static class Ad{
        /**
         * 'id': '⼴告id',
         *  'title': '⼴告标题',
         *  'target_href': '点击⼴告打开的链接',
         *  'layout': 'APP⼴告布局:1图⽚开屏，2视频开屏，3图⽚通屏，4图⽚⽆标题，5图⽚有
         * 标题，6视频⽆标题，7视频有标题，8图⽚栏⽬插屏',
         *  '': '⼴告宽度，单位px(视频时为0)',
         *  'height': '⼴告⾼度，单位px(视频时为0)',
         *  'ad_url': '⼴告资源url',
         */


        private String id;
        private String title;
        private String target_href;
        private String ad_url;
        private int layout;
        private int height;
        private int width;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTarget_href() {
            return target_href;
        }

        public void setTarget_href(String target_href) {
            this.target_href = target_href;
        }

        public String getAd_url() {
            return ad_url;
        }

        public void setAd_url(String ad_url) {
            this.ad_url = ad_url;
        }

        public int getLayout() {
            return layout;
        }

        public void setLayout(int layout) {
            this.layout = layout;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
