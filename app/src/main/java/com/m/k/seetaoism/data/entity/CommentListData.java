package com.m.k.seetaoism.data.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentListData {


    /**
     * 'start': '下次请求评论列表开始位置',
     * 'point_time': '下次请求使⽤的节点时间',
     * 'more': '是否有更多数据，1有，0没有',
     */


    private int start;

    private long point_time;

    private int more;



    private ArrayList<Comment> comment_list;


    public ArrayList<Comment> getCommentList() {
        return comment_list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getPoint_time() {
        return point_time;
    }

    public void setPoint_time(long point_time) {
        this.point_time = point_time;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public ArrayList<Comment> getComment_list() {
        return comment_list;
    }

    public void setComment_list(ArrayList<Comment> comment_list) {
        this.comment_list = comment_list;
    }
}
