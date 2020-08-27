package com.m.k.seetaoism.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cunoraz.gifview.library.GifView;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.text.DecimalFormat;

public class SmartRefreshFooter extends ConstraintLayout implements RefreshFooter {

    private GifView mGifView;
    private ImageView mMaskView;
    private DecimalFormat decimalFormat = new DecimalFormat("000");

    public SmartRefreshFooter(Context context) {
        super(context);
    }

    public SmartRefreshFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRefreshFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mGifView = findViewById(R.id.gifView);
        mMaskView = findViewById(R.id.maskView);
        mGifView.setVisibility(INVISIBLE);
        mGifView.pause();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

        Logger.d("%s,isDragging = %s,percent = %s","footer",isDragging,percent);
        if (isDragging && percent <= 1) {
            if(!mGifView.isPlaying()){
                mGifView.setVisibility(INVISIBLE);
                mMaskView.setVisibility(VISIBLE);
                int p = (int) (percent * 100) / 2; // (0 ~ 50)
                String prefixName = "header" + decimalFormat.format(p);
                int id = getResources().getIdentifier(prefixName, "drawable", getContext().getPackageName());
                if (id > 0) {
                    mMaskView.setBackgroundResource(id);
                }
            }else{
                mMaskView.setVisibility(INVISIBLE);
            }


        }

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Logger.d(" State onStartAnimator ");
        mMaskView.setVisibility(INVISIBLE);
        mGifView.setVisibility(VISIBLE);
        mGifView.play();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        Logger.d();
        Logger.d(" State onFinish ");
        mGifView.pause();
        return 500; //  加载完成后多久关闭，即回弹会去
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        Logger.d("%s oldState = %s newState  = %s","onStateChanged",oldState.name(),newState.name());
        //switch ()

    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }
}
