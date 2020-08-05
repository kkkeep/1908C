package com.m.k.seetaoism.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MinMatchParentConstraintLayout  extends ConstraintLayout {

    public MinMatchParentConstraintLayout(Context context) {
        super(context);
    }

    public MinMatchParentConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MinMatchParentConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Math.max(parentHeight,height),MeasureSpec.EXACTLY));



    }



}
