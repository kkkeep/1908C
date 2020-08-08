package com.m.k.banner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Banner extends ConstraintLayout {


    private ViewPager2 mPager;
    private TextView mTitle;

    private ArrayList<? extends IBannerData> mDatas;

    private int mIds = 0x1000;




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


        initView();

    }



    private void initView(){


        // Step1 : 添加一个ViewPager2


        mPager = new ViewPager2(getContext());
        mPager.setId(mIds++);


        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                mTitle.setText(mDatas.get(position % mDatas.size()).getTitle());

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




        // step3 添加 title


        mTitle = new TextView(getContext());
        mTitle.setId(mIds++);
        mTitle.setTextColor(Color.WHITE);
        addView(mTitle);

        constraintSet.connect(mTitle.getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        constraintSet.connect(mTitle.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);

        constraintSet.connect(mTitle.getId(),ConstraintSet.BOTTOM,mask.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mTitle.getId(),ConstraintSet.TOP,mask.getId(),ConstraintSet.TOP);

        constraintSet.constrainWidth(mTitle.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mTitle.getId(),ConstraintSet.WRAP_CONTENT);


        constraintSet.applyTo(this);


    }




    public void setData(ArrayList<? extends IBannerData> data){


        mDatas = data;

        mPager.setAdapter(new SimpleBannerAdapter(data));

        mPager.setUserInputEnabled(true);

        mPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        mPager.setCurrentItem(0);


    }



    public  int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
