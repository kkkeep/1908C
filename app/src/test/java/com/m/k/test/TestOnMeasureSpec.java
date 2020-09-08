package com.m.k.test;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m.k.seetaoism.R;

public class TestOnMeasureSpec extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_actvivity_measure);
    }


    public static class MyView extends androidx.appcompat.widget.AppCompatTextView{

        public MyView(Context context) {
            super(context);
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int mode = MeasureSpec.getMode(widthMeasureSpec);
            int size = MeasureSpec.getSize(widthMeasureSpec);

            mode = MeasureSpec.getMode(heightMeasureSpec);
            size = MeasureSpec.getSize(heightMeasureSpec);

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            Log.d("Test","");
        }
    }

    public static class MyViewGroup extends FrameLayout{



        public MyViewGroup(@NonNull Context context) {
            super(context);
        }

        public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           int w =  getChildMeasureSpec(widthMeasureSpec,0,LayoutParams.WRAP_CONTENT);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            int mode = MeasureSpec.getMode(widthMeasureSpec);
            int size = MeasureSpec.getSize(widthMeasureSpec);

            mode = MeasureSpec.getMode(heightMeasureSpec);
            size = MeasureSpec.getSize(heightMeasureSpec);



            Log.d("Test","");
        }
    }
}

