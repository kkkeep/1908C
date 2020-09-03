package com.m.k.video;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;

public class MkVideoAutoPlayScrollHelper extends RecyclerView.OnScrollListener {

    int firstVisibleItem;
    int lastVisibleItem;
    LinearLayoutManager linearLayoutManager;

    public MkVideoAutoPlayScrollHelper(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        firstVisibleItem   = linearLayoutManager.findFirstVisibleItemPosition();
        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        //大于0说明有播放
        if (GSYVideoManager.instance().getPlayPosition() >= 0) {
            //当前播放的位置
            int position = GSYVideoManager.instance().getPlayPosition();
            //对应的播放列表TAG
            if ((position < firstVisibleItem || position > lastVisibleItem)) {

                //如果滑出去了上面和下面就是否，和今日头条一样
                //是否全屏
                if(!GSYVideoManager.isFullState((Activity) recyclerView.getContext())) {
                    GSYVideoManager.releaseAllVideos();
                    recyclerView.getAdapter().notifyItemChanged(position);
                }
            }
        }
    }

    @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE){
                play(recyclerView);

        }
    }


    /**
     *
     * 从第一可见的position  到最后一个可见的position 挨个遍历
     *
     * 通过 position 找到 对应的item view
     *
     * 在通过 item view 找到 holder 对象
     *
     *
     * 如果这个holder 是一个视频广告的holder
     *          如果这个item 视频播放器全部可见 那么通过这个item view 找到 对应的播放器控件，然后调用start 播放
     *
     */



    public void play(RecyclerView recyclerView){


        // 遍历第一个可见 item 和 最后一个可见item 之间的 是否有 视频广告item
        for (int i = firstVisibleItem; i <= lastVisibleItem; i++) {

            View itemView = linearLayoutManager.findViewByPosition(i);// 根据 position  找到 item view;
            if(itemView == null){
                return;
            }

            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(itemView); // 更具 item  view 找到对应 holder

            if(holder instanceof MkAutoPlayVideoHolder){ // 如果这个holder 实现了这个接口，意味着这个holder 对应的item 里面的视频会自动播放

                MKVideo video =  ((MkAutoPlayVideoHolder) holder).getVideoView();


                int itemViewTop = itemView.getTop(); // item 在 recycler view 中的 top

                int videoHeight = video.getHeight();// 视频控件的高度

                int videoTopYInRecyclerView = itemViewTop + video.getTop(); // video view 在 recycler view （祖宗容器） 的 top 值


                if (videoTopYInRecyclerView < 0) { // 如果video view 顶部有一部分不在recycler view 里面
                    // 顶部不在 recycler  view 里面的部分小于制定的值，那么播放
                    if (Math.abs(videoTopYInRecyclerView) <= getPlayOrStopThreshold(videoHeight)) {
                        if(video.isInInPause()){
                            video.onVideoResume(false);
                        }else if(!video.isInPlayingState()){
                            video.startPrepare();
                        }
                        break;
                    }else{
                        if(video.isInPlayingState()){
                            video.onVideoPause();
                        }
                    }
                }else{
                    int videoBottomYInRecyclerView = itemView.getTop() + video.getBottom(); // video view 在 recycler vie 中的 bot
                    int excess = videoBottomYInRecyclerView - recyclerView.getHeight(); // video view 在 recycler view 中超出部分
                    //  如果video view 整个都在recycler view  里面或者 video 有一部分已经超出了 recycler view 下面一部分,但是超出部分不足 video view 高度的三分之一
                    if (excess < getPlayOrStopThreshold(videoHeight)) {
                        if(video.isInInPause()){
                            video.onVideoResume(false);
                        }else if(!video.isInPlayingState()){
                            video.startPrepare();
                        }
                        break;
                    }else{
                        if(video.isInPlayingState()){
                            video.onVideoPause();
                        }
                    }
                }
            }


        }
    }

    /**
     * 返回 video view 上边 或者 下边 垂直方向上在屏幕之外的距离，如果大于这个距离 就停止播放，小于这个距离就自动播放
     * 默认是高度的 1/3，   返回 0 表示，只有整个Video View 都在屏幕上可见是才播放。
     * @param videoHeight
     * @return
     */
    public int getPlayOrStopThreshold(int videoHeight){
        return 0;
    }



    // 看一下是否有满足自动播放的 video
    public static void playIfNeed(final RecyclerView recyclerView){
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollBy(0,1);
            }
        });
    }
}
