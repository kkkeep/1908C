package com.m.k.seetaoism;

import com.m.k.anotaion.BaseUrl;

public interface Constrant {

    @BaseUrl
    String BASE_URL = "https://www.seetao.com";

    @BaseUrl
    String BASE_URL_Test = "http://test.seetaoism.com";

    String VALUE_FROM = "ios";
    String VALUE_LANG = "zh";


    interface URL {
        String LOGIN = "app/v_1_7_2/user/login2";
        String GET_USER = "/api/user/getuserinfo";
        String COLUMN_MANAGER = "/api/column/columnmanagelist";
        String RECOMMEND_LIST = "/app/v_1_6/article/recommendlist";
        String VIDEO_LIST = "/app/v_1_6/article/videolist";
        String SPECIAL_LIST = "/app/v_1_6/article/speciallist";
        String DETAIL_RELATIVE_NEWS = "/api/article/articleaccess";
        String DETAIL_COMMENT_LIST = "/api/comment_reply/commentlist";
        String DETAIL_SHARE = "/api/user/sharearticleaddintegral";
        String DETAIL_COMMENT_NEWS = "/api/comment_reply/usercomment";
        String DETAIL_USER_REPLAY = "/api/comment_reply/userreply";
        String DETAIL_DO_COMMENT_LIKE = "/api/comment_reply/commentpraise";
    }


    interface RequestKey {


        String KEY_FROM = "from";
        String KEY_LANG = "lang";
        String KEY_TIMESTAMP = "timestamp";
        String KEY_NONCE = "nonce";
        String KEY_SIGNATURE = "signature";


        String KEY_USER_ACCOUNT = "username";
        String KEY_USER_PASSWORD = "password";
        String KEY_CODE = "code";
        String KEY_TOKEN = "token";

        String KEY_START = "start";
        String KEY_NUMBER = "number";
        String KEY_POINT_TIME = "point_time";
        String KEY_COLUMN_ID = "id";
        String KEY_NEWS_ID = "id";
        String KEY_NEWS_ID_2 = "article_id";
        String KEY_CONTENT = "content";
        String KEY_COMMENT_ID = "comment_id";
        String KEY_TO_IOD = "to_id";
        String KEY_REPLY_TYPE= "type";
        String KEY_REPLAY_ID = "reply_id";


    }
}
