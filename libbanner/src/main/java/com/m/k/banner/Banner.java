package com.m.k.banner;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Banner extends ConstraintLayout {



    private static final int DEFAULT_INTERVAL = 3000;

    private ViewPager2 mPager;
    private TextView mTitle;

    private Indicator mIndicator;

    private ArrayList<? extends IBannerData> mDatas;

    private int mIds = 0x1000;

    private int mIndicatorEndMargin;

    private int mInterval;  // 切换间隔时间
    private boolean isAutoLoop = true; // 是否自动循环




    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        initValue();
        initView();

    }


    private void initValue(){
        mIndicatorEndMargin = dip2px(15);


        mInterval = DEFAULT_INTERVAL;
    }



    private void initView(){


        // Step1 : 添加一个ViewPager2

        mPager = new ViewPager2(getContext());
        mPager.setId(mIds++);


        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                mTitle.setText(mDatas.get(position % mDatas.size()).getTitle());

                mIndicator.setCurrent(position % mDatas.size());
            }
        });


        addView(mPager);


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        constraintSet.connect(mPager.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mPager.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        constraintSet.connect(mPager.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(mPager.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);

        constraintSet.constrainWidth(mPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mPager.getId(),ConstraintSet.MATCH_CONSTRAINT);


        // Step2： 添加一个Title 的 半透明背景


        ImageView mask = new ImageView(getContext());
        mask.setBackgroundColor(Color.parseColor("#40000000"));
        mask.setId(mIds++);
        addView(mask);


        constraintSet.connect(mask.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mask.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        constraintSet.connect(mask.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mask.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mask.getId(),dip2px(50));


        // step3 添加indicator


        mIndicator = new CircleIndicator(getContext());
        mIndicator.setUnSelectColor(Color.WHITE); // TODO
        mIndicator.setSelectColor(Color.RED);
        mIndicator.setRadio(10);
        mIndicator.setMargin(10);
        mIndicator.setId(mIds++);
        addView((View) mIndicator);

        constraintSet.connect(mIndicator.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,mIndicatorEndMargin);
        constraintSet.connect(mIndicator.getId(),ConstraintSet.BOTTOM,mask.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mIndicator.getId(),ConstraintSet.TOP,mask.getId(),ConstraintSet.TOP);

        constraintSet.constrainWidth(mIndicator.getId(),ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(mIndicator.getId(),ConstraintSet.WRAP_CONTENT);

        // step4 添加 title


        mTitle = new TextView(getContext());
        mTitle.setId(mIds++);
        mTitle.setTextColor(Color.WHITE);
        mTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTitle.setSelected(true);
        mTitle.setSingleLine(true);
        mTitle.setMarqueeRepeatLimit(-1);

        addView(mTitle);

        constraintSet.connect(mTitle.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mTitle.getId(),ConstraintSet.END,mIndicator.getId(),ConstraintSet.START,mIndicatorEndMargin);

        constraintSet.connect(mTitle.getId(),ConstraintSet.BOTTOM,mask.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mTitle.getId(),ConstraintSet.TOP,mask.getId(),ConstraintSet.TOP);

        constraintSet.constrainWidth(mTitle.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mTitle.getId(),ConstraintSet.WRAP_CONTENT);


        constraintSet.applyTo(this);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                stopLoop();
                break;
            }

            case MotionEvent.ACTION_UP:{
                startLoop();
            }
        }


        return super.dispatchTouchEvent(ev);


    }

    public void setData(ArrayList<? extends IBannerData> data){

        mDatas = data;

        mPager.setAdapter(new SimpleBannerAdapter(data));

        mPager.setUserInputEnabled(true);

        mPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        int initPosition = Integer.MAX_VALUE /2;

        initPosition  =  initPosition - (initPosition % data.size());

        mPager.setCurrentItem(initPosition);

        mIndicator.setCount(data.size());
        mIndicator.setCurrent(initPosition % mDatas.size());

    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if(visibility == VISIBLE){
            startLoop();
        }else{
            stopLoop();
        }
    }


    private Runnable mLoopTask = new Runnable() {
        @Override
        public void run() {
            int cIndex = mPager.getCurrentItem();
            mPager.setCurrentItem(++cIndex, true);
            getHandler().postDelayed(this, mInterval);
        }
    };

    private void startLoop(){
        if(isAutoLoop  && mDatas != null &&  mDatas.size() > 1){
            getHandler().postDelayed(mLoopTask, mInterval);
        }
    }




    private void stopLoop(){
        getHandler().removeCallbacks(mLoopTask);
    }


    public  int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
