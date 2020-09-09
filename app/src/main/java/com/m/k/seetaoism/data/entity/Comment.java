package com.m.k.seetaoism.data.entity;

import java.util.List;

public class Comment {

    /**
     * 'comment_id': '评论id',
     * 		'type': '评论类型，1主评论，2评论的回复',
     * 		'reply_id': '回复id',
     * 		'user_id': '评论人id',
     * 		'content': '评论内容',
     * 		'username': '评论人名称',
     * 		'head_url': '评论人头像',
     * 		'time_describe': '评论时间',
     * 		'praise_count_describe': '被点赞数量',
     * 		'is_praise': '是否被当前用户点过赞，1点过，0没点过',
     * 		'reply_start': '下次请求评论回复列表开始位置',
     * 		'reply_more': '评论回复列表是否有更多数据，1有，0没有',
     *
     * 	     'reply_list' : '当前这条评论的回复列表'
     */



    private String comment_id;
    private String reply_id;
    private String user_id;
    private String content;
    private String username;
    private String head_url;
    private String time_describe;

    private int type;
    private int praise_count_describe;
    private int is_praise;

    private int reply_start;
    private long replay_point_time;

    private int reply_more;


    private List<Replay> reply_list;


    public void setReplyMore(int more) {
        this.reply_more = more;
    }

    public void setReplyStart(int replyStart) {
        this.reply_start = replyStart;
    }

    public long getReplayPointTime() {
        return replay_point_time;
    }

    public void setReplayPointTime(long replayPointTime) {
        this.replay_point_time = replayPointTime;
    }

    public String getCommentId() {
        return comment_id;
    }

    public String getReplyId() {
        return reply_id;
    }

    public String getUserId() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return username;
    }

    public String getHeadUrl() {
        return head_url;
    }

    public String getTimeDescribe() {
        return time_describe;
    }

    public int getType() {
        return type;
    }

    public int getPraiseCountDescribe() {
        return praise_count_describe;
    }

    public void incrementLikeCount(){
        praise_count_describe++;
    }

    public boolean isPraise() {
        return is_praise == 1;
    }

    public int getReplyStart() {
        return reply_start;
    }

    public int getReplyMore() {
        return reply_more;
    }

    public List<Replay> getReplyList() {
        return reply_list;
    }

    public void setReplyList(List<Replay> reply_list) {
        this.reply_list = reply_list;
    }

    public void setIsPraise(int isPraise) {
        this.is_praise = isPraise;
    }

    public void addLikeCount(){
        this.praise_count_describe++;
    }
}
