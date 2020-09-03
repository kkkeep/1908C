package com.m.k.video;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.libvideo.R;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import moe.codeest.enviews.ENPlayView;

public class MkEmptyVideo extends MKVideo {



    public MkEmptyVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MkEmptyVideo(Context context) {
        super(context);
    }

    public MkEmptyVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public int getLayoutId() {
        return R.layout.lib_video_empty_layout;
    }




    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;

        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;

        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    protected void touchDoubleUp() {
       // super.touchDoubleUp();
        //不需要双击暂停
    }


}
