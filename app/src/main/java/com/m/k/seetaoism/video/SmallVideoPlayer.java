package com.m.k.seetaoism.video;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.view.SmallVideoTouch;

import java.lang.reflect.Constructor;

import static com.shuyu.gsyvideoplayer.utils.CommonUtil.getActionBarHeight;

public class SmallVideoPlayer extends NormalGSYVideoPlayer {

    private OnPlayCallBack mOnPlayCallBack;


    private boolean isSmall;
    public SmallVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SmallVideoPlayer(Context context) {
        super(context);
    }

    public SmallVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

    }


    public void setOnPlayCallBack(OnPlayCallBack onPlayCallBack) {
        this.mOnPlayCallBack = onPlayCallBack;
    }

    public GSYBaseVideoPlayer showSmallVideo(Point size, boolean actionBar, int marginBottom) {
        final ViewGroup vp = (ViewGroup) (CommonUtil.scanForActivity(getContext())).findViewById(Window.ID_ANDROID_CONTENT);

        removeVideo(vp, getSmallId());

        if (mTextureViewContainer.getChildCount() > 0) {
            mTextureViewContainer.removeAllViews();
        }

        try {
            Constructor<SmallVideoPlayer> constructor = (Constructor<SmallVideoPlayer>) SmallVideoPlayer.this.getClass().getConstructor(Context.class);
            SmallVideoPlayer gsyVideoPlayer = constructor.newInstance(getActivityContext());
            gsyVideoPlayer.setId(getSmallId());

            LayoutParams lpParent = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            FrameLayout frameLayout = new FrameLayout(mContext);

            LayoutParams lp = new LayoutParams(size.x, size.y);
            int marginLeft = CommonUtil.getScreenWidth(mContext) - size.x;
            int marginTop = vp.getHeight() - size.y;

            if (actionBar) {
                marginTop = marginTop - getActionBarHeight((Activity) mContext);
            }

            lp.setMargins(marginLeft, marginTop - marginBottom, 0, 0);
            frameLayout.addView(gsyVideoPlayer, lp);

            vp.addView(frameLayout, lpParent);

            cloneParams(this, gsyVideoPlayer);

            gsyVideoPlayer.setIsTouchWiget(false);//小窗口不能点击

            gsyVideoPlayer.addTextureView();
            //隐藏掉所有的弹出状态哟
            gsyVideoPlayer.onClickUiToggle();
            gsyVideoPlayer.setVideoAllCallBack(mVideoAllCallBack);
            gsyVideoPlayer.setSmallVideoTextureView(new SmallVideoTouch(gsyVideoPlayer, marginLeft, marginTop));

            getGSYVideoManager().setLastListener(this);
            getGSYVideoManager().setListener(gsyVideoPlayer);
            if (mVideoAllCallBack != null) {
                Debuger.printfError("onEnterSmallWidget");
                mVideoAllCallBack.onEnterSmallWidget(mOriginUrl, mTitle, gsyVideoPlayer);
            }

            return gsyVideoPlayer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 移除没用的
     */
    private void removeVideo(ViewGroup vp, int id) {
        View old = vp.findViewById(id);
        if (old != null) {
            if (old.getParent() != null) {
                ViewGroup viewGroup = (ViewGroup) old.getParent();
                vp.removeView(viewGroup);
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }


    @Override
    public void releaseVideos() {
        super.releaseVideos();
    }

    public void setSmall(){
        isSmall = true;
    }

    @Override
    protected void changeUiToCompleteShow() {
        super.changeUiToCompleteShow();

        mBackButton.setVisibility(INVISIBLE);
        mTotalTimeTextView.setVisibility(INVISIBLE);

        mBottomContainer.setVisibility(INVISIBLE);
        mStartButton.setVisibility(INVISIBLE);


    }

    //
    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        if(mOnPlayCallBack != null){
            mOnPlayCallBack.onComplete();
        }
    }

    @Override
    public void startAfterPrepared() {
        super.startAfterPrepared();
        if(mOnPlayCallBack != null){
            mOnPlayCallBack.onStart();
        }
    }



    @Override
    public void onPrepared() {
        super.onPrepared();
    }

    @Override
    public void startPlayLogic() {
        super.startPlayLogic();
    }

    @Override
    protected void startPrepare() {
        super.startPrepare();
    }

    @Override
    public void setSeekOnStart(long seekOnStart) {
        super.setSeekOnStart(seekOnStart);
    }

    @Override
    public void seekTo(long position) {
        super.seekTo(position);
    }


    public interface OnPlayCallBack {
       default void onComplete(){};

       default void onStart(){};
    }
}
