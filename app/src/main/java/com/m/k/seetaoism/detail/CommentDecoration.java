package com.m.k.seetaoism.detail;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.mvp.utils.MvpUtils;

import retrofit2.http.PATCH;

public class CommentDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;

    private int mStartPosition;

    private Context mContext;
    private int x,y;


    public CommentDecoration(int startPosition) {

        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        mStartPosition = startPosition;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        if(mContext == null){
           mContext = parent.getContext().getApplicationContext();
            paint.setTextSize(MvpUtils.dip2px(mContext,16));
            x = MvpUtils.dip2px(mContext,16);
            y = MvpUtils.dip2px(mContext,15);

        }
        View v = null;

        for(int i = 0; i < parent.getChildCount(); i++){


            v = parent.getChildAt(i);


            int position =  parent.getChildAdapterPosition(v);

            if(position == mStartPosition){
                outRect.set(0, MvpUtils.dip2px(parent.getContext(),50),0,0);

            }else{
                outRect.set(0,0,0,0);
            }

        }


    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        View v = null;

        for(int i = 0; i < parent.getChildCount(); i++){


            v = parent.getChildAt(i);


            int position =  parent.getChildAdapterPosition(v);

            if(position == mStartPosition){
                int top =  v.getTop();


                c.drawText("全部评论",x,top - y,paint);
                //c.drawRect(new Rect(0,top - 100,v.getRight(),top),paint);
            }

        }

    }

}
