package com.m.k.mvp.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

public class BottomNavigation extends ConstraintLayout {


    private NavigationAdapter mAdapter;

    private ArrayList<Integer> mDrawableRes = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private int mMarginLeft;
    private int mMarginBottom;
    private int mMarginRight;
    private int mMarginTop;

    public BottomNavigation(Context context) {
        super(context);
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }






    public BottomNavigation addItem(@DrawableRes  int drawableId, String title){

        mDrawableRes.add(drawableId);
        mTitles.add(title);
        return this;
    }


    public void apply(){
        // 把这些drawable 和 title  应用到 我的布局上
        if(mDrawableRes.size() > 0 ){

            ArrayList<TabData> list = new ArrayList<>();

            for(int i = 0; i < mDrawableRes.size(); i++){
                list.add(new TabData(mDrawableRes.get(i),mTitles.get(i)));

            }
            mAdapter = new SimpleNavigationAdapter(list);

            initView();
        }

    }


    private void initView(){

        removeAllViews();

        for(int i = 0; i < mAdapter.getCount(); i++){
            TabHolder holder = mAdapter.createHolder(this,i);
            addView(holder.mItemView);
            mAdapter.bindData(holder,i);
        }


        // 添加约束条件
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        int previousId = 0; // 用于记录上一个 id
        View view;
        int ids [] = new int[mAdapter.getCount()];

        // 在使用链的时候，如果是水平的链，那么就只需要把链上的所有控件的垂直方向上的约束添加上即可

        for(int i = 0; i < mAdapter.getCount(); i++){

            view = mAdapter.getHolderByPosition(i).mItemView;

            ids[i] = view.getId();

            if (i == 0) { // 第 0 个 tab
                constraintSet.connect(view.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 20);
                constraintSet.connect(view.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 20);
            } else {
                constraintSet.connect(view.getId(), ConstraintSet.BOTTOM, previousId, ConstraintSet.BOTTOM);
            }
            constraintSet.constrainWidth(view.getId(), ConstraintSet.WRAP_CONTENT);
            constraintSet.constrainHeight(view.getId(), ConstraintSet.WRAP_CONTENT);

            previousId = view.getId();


        }



        // 第一个参数： 你这个链的左端需要连接到的控件的Id
        // 第二个参数： 你这个链的左端需要连接到第一个参数指定的控件的那一边
        // 第三个参数： 你这个链的右端需要连接大的控件的Id
        // 第四个参数： 你这个链的右端需要连接到第三个参数指定的控件的那一边
        // 第五个参数： 你这个链上多有控件的Id 的一个数组
        // 第六个参数： 权重
        // 第七个参数： 链的模式
        constraintSet.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, ids, null, ConstraintSet.CHAIN_SPREAD_INSIDE);

        constraintSet.applyTo(this);

        setPadding(20,0,20,0);
    }





    public static class SimpleNavigationAdapter implements NavigationAdapter<SimpleNavigationAdapter.SimpleTabHolder>{

        private int mId = 1000;

        private ArrayList<TabData> mTabData;

        private ArrayList<SimpleTabHolder> mHolders = new ArrayList<>();

        SimpleNavigationAdapter(ArrayList<TabData> tabData) {
            this.mTabData = tabData;
        }

        @Override
        public SimpleTabHolder createHolder(ViewGroup parent, int position) {

            CheckBox tabView = new CheckBox(parent.getContext());
            tabView.setId(mId + position);
            tabView.setButtonDrawable(null);
            tabView.setGravity(Gravity.CENTER);
            SimpleTabHolder holder =  new SimpleTabHolder(tabView);

            mHolders.add(holder);
            return holder;
        }

        @Override
        public void bindData(SimpleTabHolder holder, int position) {

            Drawable topDrawable = holder.mItemView.getContext().getResources().getDrawable(mTabData.get(position).getDrawableId());
            holder.mItemView.setCompoundDrawablesWithIntrinsicBounds(null,topDrawable,null,null);
            holder.mItemView.setText(mTabData.get(position).getTitle());

        }

        @Override
        public int getCount() {
            return mTabData == null ? 0 : mTabData.size();
        }


        @Override
        public SimpleTabHolder getHolderByPosition(int position) {
            return mHolders.size() == 0 ? null : mHolders.get(position);
        }

        static class SimpleTabHolder extends TabHolder<CheckBox>{

            SimpleTabHolder(CheckBox itemView) {
                super(itemView);
            }

        }

    }


    private static abstract class TabHolder<T extends View> {
         T mItemView;

        TabHolder(T itemView) {
            this.mItemView = itemView;
        }

    }

    public interface NavigationAdapter<TH extends TabHolder>{

        TH createHolder(ViewGroup parent, int position);

        void bindData(TH holder,int position);

        int getCount();

        TH getHolderByPosition(int position);

    }

    private static class TabData{
        private int drawableId;
        private String title;

        TabData(int drawableId, String title) {
            this.drawableId = drawableId;
            this.title = title;
        }

        int getDrawableId() {
            return drawableId;
        }

        String getTitle() {
            return title;
        }
    }
}
