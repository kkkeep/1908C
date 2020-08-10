package com.m.k.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleIndicator extends View implements Indicator {

    private static final int MAX_COUNT = 10;

    private Paint mPaint;




    private int mCurrentIndex;
    private int mUnSelectColor;
    private int mSelectColor;
    private int mHeight;
    private int mWidth;
    private int mRatio;
    private int mMargin;
    private int mCount; // 最终显示个数
    private int mRealCount; // 实际个数






    public CircleIndicator(Context context) {
        super(context);
        initPain();
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPain();
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPain();
    }



    private void initPain(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 去锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
    }


    private void calculation(){
        mHeight = mRatio * 2;

        mCount = Math.min(mRealCount,MAX_COUNT);

        mWidth = (mCount * mRatio * 2) + (mCount - 1) * mMargin;
        invalidate(); // 刷新页面，重新onMeasure onLayout,onDraw
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(mWidth,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(mHeight,MeasureSpec.EXACTLY));
    }



    @Override
    protected void onDraw(Canvas canvas) {

       // canvas.drawColor(Color.RED);
        for(int i = 0; i < mCount; i++){

            // 第一：cX  = 0 * （2* mRadio） + (0 * mMargin) +  mRadio, cY = mRadio
            // 第二：cx = 1* （2* mRadio） + （1* margin） + mRadio ,   cy = mRadio
            // 第三：cx = 2* (2 * mRadio) + (2 * margin) + mRadio ,   cY = mRadio

            if(i == mCurrentIndex % mCount){
                mPaint.setColor(mSelectColor);
            }else{
                mPaint.setColor(mUnSelectColor);
            }


            canvas.drawCircle((i * (2 * mRatio) + (i * mMargin) + mRatio),mRatio,mRatio,mPaint);
        }


    }

    @Override
    public void setRadio(int radio) {
        mRatio = radio;


    }
    @Override
    public void setMargin(int margin) {
        mMargin = margin;
    }

    @Override
    public void setCount(int count) {

        if(mRealCount != count){
            mRealCount = count;
            calculation();
        }
    }








    @Override
    public void setCurrent(int index) {

        if(mCurrentIndex != index){
            Log.d("CircleIndicator","setCurrent");
            mCurrentIndex = index;
            invalidate();
        }

    }

    @Override
    public void setUnSelectColor(int color) {
        Log.d("CircleIndicator","setUnSelectColor");
        mUnSelectColor = color;
    }

    @Override
    public void setSelectColor(int color) {
        Log.d("CircleIndicator","setSelectColor");

        mSelectColor = color;
    }



}
