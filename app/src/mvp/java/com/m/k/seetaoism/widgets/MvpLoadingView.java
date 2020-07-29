package com.m.k.seetaoism.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
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

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_POP, MODE_FULL})
    public @interface LoadingMode { }


    public MvpLoadingView(Context context) {
        super(context);
    }

    public MvpLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs); }

    public MvpLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);}

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
        return true;
    }

    public void setParentContainer(ViewGroup parent){

        mParent = parent;
    }


    public void showLoading(@LoadingMode int mode) {

        String parentClassName = mParent.getClass().getName();
        if(parentClassName.equals(RelativeLayout.class.getName())|| parentClassName.equals(FrameLayout.class.getName()) || parentClassName.equals(ContentFrameLayout.class.getName())){
            mParent.addView(this,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }else if(mParent instanceof ConstraintLayout){

            mParent.addView(this);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) mParent);

            constraintSet.connect(getId(),ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
            constraintSet.connect(getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
            constraintSet.connect(getId(),ConstraintSet.END,mParent.getId(),ConstraintSet.END);
            constraintSet.connect(getId(),ConstraintSet.BOTTOM,mParent.getId(),ConstraintSet.BOTTOM);

            constraintSet.applyTo((ConstraintLayout) mParent);


        }



        mErrorPage.setVisibility(GONE);
        if(mode == MODE_POP){
            setBackgroundColor(Color.TRANSPARENT);
            mGifBackgroundView.setVisibility(VISIBLE);
        }else{
            setBackgroundColor(Color.WHITE);
            mGifBackgroundView.setVisibility(GONE);
        }


    }


    public void closeLoading(){
        if(mParent != null){
            mParent.removeView(this);
        }
    }
}
