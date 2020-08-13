package com.k.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MyConstraintLatyou extends ConstraintLayout {

    public MyConstraintLatyou(Context context) {
        super(context);
    }

    public MyConstraintLatyou(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyConstraintLatyou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
