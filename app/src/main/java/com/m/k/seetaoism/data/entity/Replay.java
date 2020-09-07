package com.m.k.seetaoism.data.entity;


import com.m.k.mvp.widgets.CommentsView;

public class Replay  implements CommentsView.ReplayData {


    /**
     *          'reply_id': '回复id',
     * 			'type': '评论类型，1主评论，2评论的回复',
     * 			'comment_id': '评论id',
     * 			'content': '回复内容',
     * 			'from_name': '回复人名称',
     *          'to_name': '被回复人名称',
     *          'from_id': '回复人id',
     * 	        'to_id': '被回复人id',
     */


    private String reply_id;
    private int type;
    private String comment_id;
    private String content;
    private String from_name;
    private String to_name;
    private String from_id;
    private String to_id;


    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String getFromUserName() {
        return from_name;
    }

    @Override
    public String getToUserName() {
        return to_name;
    }



    public String getContent() {
        return content;
    }

    @Override
    public String getFromUserId() {
        return from_id;
    }

    @Override
    public String getToUserId() {
        return to_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }
}
