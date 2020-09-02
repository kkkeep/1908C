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

public class MKVideo extends StandardGSYVideoPlayer {


    private ImageView mCoverImage;

    public MKVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MKVideo(Context context) {
        super(context);
    }

    public MKVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
       // super.init(context);


        // GSYVideoView int
        if(getActivityContext() != null) {
            this.mContext = getActivityContext();
        } else {
            this.mContext = context;
        }

        initInflate(mContext);

        mTextureViewContainer = (ViewGroup) findViewById(R.id.surface_container);
        if (isInEditMode())
            return;
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        mAudioManager = (AudioManager) mContext.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);



        // GSYVideoControlView
        mStartButton = findViewById(R.id.start);
        mTitleTextView = (TextView) findViewById(R.id.title);
        mBackButton = (ImageView) findViewById(R.id.back);
        mFullscreenButton = (ImageView) findViewById(R.id.fullscreen);
        mProgressBar = (SeekBar) findViewById(R.id.progress);
        mCurrentTimeTextView = (TextView) findViewById(R.id.current);
        mTotalTimeTextView = (TextView) findViewById(R.id.total);
        mBottomContainer = (ViewGroup) findViewById(R.id.layout_bottom);
        mTopContainer = (ViewGroup) findViewById(R.id.layout_top);
        mBottomProgressBar = (ProgressBar) findViewById(R.id.bottom_progressbar);
        mThumbImageViewLayout = (RelativeLayout) findViewById(R.id.thumb);
        mLockScreen = (ImageView) findViewById(R.id.lock_screen);

        mLoadingProgressBar = findViewById(R.id.loading);


        if (isInEditMode())
            return;

        if (mStartButton != null) {
            mStartButton.setOnClickListener(this);
        }

        if (mFullscreenButton != null) {
            mFullscreenButton.setOnClickListener(this);
            mFullscreenButton.setOnTouchListener(this);
        }

        if (mProgressBar != null) {
            mProgressBar.setOnSeekBarChangeListener(this);
        }

        if (mBottomContainer != null) {
            mBottomContainer.setOnClickListener(this);
        }

        if (mTextureViewContainer != null) {
            mTextureViewContainer.setOnClickListener(this);
            mTextureViewContainer.setOnTouchListener(this);
        }

        if (mProgressBar != null) {
            mProgressBar.setOnTouchListener(this);
        }

        if (mThumbImageViewLayout != null) {
            mThumbImageViewLayout.setVisibility(GONE);
            mThumbImageViewLayout.setOnClickListener(this);
        }
        if (mThumbImageView != null && !mIfCurrentIsFullscreen && mThumbImageViewLayout != null) {
            mThumbImageViewLayout.removeAllViews();
            resolveThumbImage(mThumbImageView);
        }

        if (mBackButton != null)
            mBackButton.setOnClickListener(this);

        if (mLockScreen != null) {
            mLockScreen.setVisibility(GONE);
            mLockScreen.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCurrentState == CURRENT_STATE_AUTO_COMPLETE ||
                            mCurrentState == CURRENT_STATE_ERROR) {
                        return;
                    }
                    lockTouchLogic();
                    if (mLockClickListener != null) {
                        mLockClickListener.onClick(v, mLockCurScreen);
                    }
                }
            });
        }

        if (getActivityContext() != null) {
            mSeekEndOffset = CommonUtil.dip2px(getActivityContext(), 50);
        }



        // GSYBaseVideoPlayer
        mSmallClose = findViewById(R.id.small_close);

        //

        //StandardGSYVideoPlayer

        if (mBottomProgressDrawable != null) {
            mBottomProgressBar.setProgressDrawable(mBottomProgressDrawable);
        }

        if (mBottomShowProgressDrawable != null) {
            mProgressBar.setProgressDrawable(mBottomProgressDrawable);
        }

        if (mBottomShowProgressThumbDrawable != null) {
            mProgressBar.setThumb(mBottomShowProgressThumbDrawable);
        }



        mCoverImage = new ImageView(getContext());
        mCoverImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setThumbImageView(mCoverImage);

        //
    }


    public ImageView getCoverView(){
        return mCoverImage;
    }


    @Override
    public int getLayoutId() {
        return R.layout.lib_video_layout;
    }


    // 自定义 开始/ 暂停/ 错误的图标
    @Override
    protected void updateStartImage() {
        if (mStartButton instanceof ENPlayView) {
            ENPlayView enPlayView = (ENPlayView) mStartButton;
            enPlayView.setDuration(500);
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                enPlayView.play();
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                enPlayView.pause();
            } else {
                enPlayView.pause();
            }
        } else if (mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) mStartButton;
            if (mCurrentState == CURRENT_STATE_PLAYING) {
                imageView.setImageResource(R.drawable.video_click_pause_selector);
            } else if (mCurrentState == CURRENT_STATE_ERROR) {
                imageView.setImageResource(R.drawable.video_click_error_selector);
            } else {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            }
        }
    }


}
