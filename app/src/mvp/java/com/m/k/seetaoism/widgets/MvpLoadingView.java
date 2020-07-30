package com.m.k.seetaoism.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Group;

import com.cunoraz.gifview.library.GifView;
import com.m.k.seetaoism.R;

import org.w3c.dom.Text;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MvpLoadingView extends ConstraintLayout {

    public static final int MODE_POP = 1;
    public static final int MODE_FULL = 2;

    private ImageView mGifBackgroundView;
    private GifView mGifView;
    private Group mErrorPage;
    private ImageView mErrorIcon;
    private TextView mErrorMessage;
    private Button mRetry;

    private ViewGroup mParent;


    private int mCurrentMode;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_POP, MODE_FULL})
    public @interface LoadingMode {
    }


    public MvpLoadingView(Context context) {
        super(context);
    }

    public MvpLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MvpLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setId(10000);
        mGifBackgroundView = findViewById(R.id.mvp_loading_iv_gif_bg_view);
        mGifView = findViewById(R.id.mvp_loading_gif_view);
        mErrorPage = findViewById(R.id.mvp_loading_group_error);
        mErrorIcon = findViewById(R.id.mvp_loading_iv_error_icon);
        mErrorMessage = findViewById(R.id.mvp_loading_tv_error_content);
        mRetry = findViewById(R.id.mvp_loading_btn_retry);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void setParentContainer(ViewGroup parent) {
        mParent = parent;
    }


    public static MvpLoadingView inject(ViewGroup parent) {

        // 为了避免重复添加,那么判断一下传进来的 parent 上是否已经添加了loading view


        View child;
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            child = parent.getChildAt(i);
            if (child instanceof MvpLoadingView) {
                return (MvpLoadingView) child;
            }
        }


        MvpLoadingView loadingView = (MvpLoadingView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_view, parent, false);

        loadingView.setParentContainer(parent);

        return loadingView;
    }

    public void showLoading(@LoadingMode int mode) {

        if (this.getParent() == null) {

            String parentClassName = mParent.getClass().getName();
            if (parentClassName.equals(RelativeLayout.class.getName()) || parentClassName.equals(FrameLayout.class.getName()) || parentClassName.equals(ContentFrameLayout.class.getName())) {
                mParent.addView(this, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else if (mParent instanceof ConstraintLayout) {

                mParent.addView(this);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) mParent);

                constraintSet.connect(getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                constraintSet.connect(getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                constraintSet.connect(getId(), ConstraintSet.END, mParent.getId(), ConstraintSet.END);
                constraintSet.connect(getId(), ConstraintSet.BOTTOM, mParent.getId(), ConstraintSet.BOTTOM);
                constraintSet.applyTo((ConstraintLayout) mParent);


            }
        }

        if(mCurrentMode == mode){
            return;
        }


        mCurrentMode = mode;

        mErrorPage.setVisibility(GONE);
        if (mode == MODE_POP) {
            setBackgroundColor(Color.TRANSPARENT);
            mGifBackgroundView.setVisibility(VISIBLE);
        } else {
            setBackgroundColor(Color.WHITE);
            mGifBackgroundView.setVisibility(GONE);
        }
        mGifView.play();

    }

    public void onError(){


        mGifView.pause();
        mGifView.setVisibility(GONE);
        mGifBackgroundView.setVisibility(GONE);

        mErrorPage.setVisibility(VISIBLE);

        if(mCurrentMode != MODE_FULL){
            setBackgroundColor(Color.WHITE);
        }

        // mRetry 要不要显示，要显示肯定就有有重试的功能，那么重试功能如何实现？
        // mErrorMessage 这个 textView ，是显示默认的，还是显示app 传过来的。
        // ErrorIcon  要不要显示？ 要显示，是显示默认的，还是 app 传过来的
    }

    /**
     * 第一种出错误：不需要重试，用默认的错误消息
     * 第二种：不需要重试，用自己的消息提示
     * 第四种，需要重试，用默人消息
     * 第五中：需要重试，用自己的消息
     * 第六种：现不现实错误icon
     */



    public void closeLoading() {
        if (mParent != null) {
            mParent.removeView(this);
        }
    }
}
