package com.m.k.seetaoism.video;

import android.content.Context;
import android.graphics.Point;
import android.view.View;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.lang.reflect.Field;

public class SmallVideoHelper extends GSYVideoHelper {

    private SmallVideoPlayer mSmallPlayer;
    public SmallVideoHelper(Context context) {
        super(context);
    }

    public SmallVideoHelper(Context context, StandardGSYVideoPlayer player) {
        super(context, player);
    }

    public void showSmallVideo(Point size, boolean actionBar,int marginBottom) {

        if (getGsyVideoPlayer().getCurrentState() == GSYVideoPlayer.CURRENT_STATE_PLAYING) {
            mSmallPlayer = (SmallVideoPlayer) ((SmallVideoPlayer) getGsyVideoPlayer()).showSmallVideo(size, actionBar,marginBottom);
           setSmallMode();
        }

    }

    public void closeSmallVideo(){
        mSmallPlayer.hideSmallVideo();
        mSmallPlayer.releaseVideos();
    }

    private void setSmallMode(){
        Class aClass = getClass().getSuperclass();

        try {
            Field field = aClass.getDeclaredField("isSmall");

            field.setAccessible(true);

            field.set(this,true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();

        }
    }
}
