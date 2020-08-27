package com.m.k.seetaoism.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cunoraz.gifview.library.GifView;
import com.m.k.seetaoism.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.text.DecimalFormat;

public class SmartRefreshHeader extends ConstraintLayout implements RefreshHeader {

    private GifView mGifView;
    private ImageView mMaskView;
    private DecimalFormat decimalFormat = new DecimalFormat("000");

    public SmartRefreshHeader(Context context) {
        super(context);
    }

    public SmartRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mGifView.setVisibility( offset > 1 ? INVISIBLE : VISIBLE);
        mMaskView.setVisibility(offset > 1 ? VISIBLE : INVISIBLE);
        if (isDragging && percent <= 1) {
            int p = (int) (percent * 100) / 2; // (0 ~ 50)
            String prefixName = "header" + decimalFormat.format(p);
            int id = getResources().getIdentifier(prefixName, "drawable", getContext().getPackageName());
            if (id > 0) {
                mMaskView.setBackgroundResource(id);
            }
        }

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mMaskView.setVisibility(INVISIBLE);
        mGifView.setVisibility(VISIBLE);
        mGifView.play();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mGifView.pause();
        return 500;
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

        //switch ()
    }
}
