package com.m.k.seetaoism.home.recommend;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.net.request.GetRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.databinding.FragmentRecommendBinding;
import com.m.k.seetaoism.home.recommend.page.PageFragment;
import com.m.k.seetaoism.utils.Logger;
import com.m.k.seetaoism.utils.ParamsUtils;

import java.util.ArrayList;

public class RecommendFragment extends BaseSmartFragment1<ColumnData> {

    FragmentRecommendBinding binding;
    private int current;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }


    private boolean isDragging;
    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binding = FragmentRecommendBinding.bind(view);


        binding.newsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.d(" position = %s positionOffset = %s ,current = %s", position, positionOffset, current);
                if(isDragging){
                    changeTabColor(position,positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) { }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state == ViewPager.SCROLL_STATE_DRAGGING){ // 手指按在屏幕上没有松开时
                    isDragging = true;
                }else {
                    isDragging = false;
                    if(state == ViewPager.SCROLL_STATE_SETTLING){ // 手指抬起，自动滑动，如果
                        changeTabColor(binding.newsViewPager.getCurrentItem(),0);
                    }
                }
            }
        });


        loadColumnData();
    }



    private void changeTabColor(int position, float offset) {

        int current =  binding.newsViewPager.getCurrentItem();


        int next;
        if(current > position){ // 反着滑动
            next = current - 1;
        }else{
            next  = current + 1;
        }

        if(next >= current){
            if(offset > 0.2){
                setTabViewTextColor(current,Color.BLACK);
                binding.slidingTabLayout.setIndicatorColor(getColumnColor(next));
            }else if( offset >= 0){
                setTabViewTextColor(current,Color.WHITE);
                binding.slidingTabLayout.setIndicatorColor(getColumnColor(current));
            }
        }else {

            if(offset < 0.8){
                setTabViewTextColor(current,Color.BLACK);
                binding.slidingTabLayout.setIndicatorColor(getColumnColor(next));
            }else if (offset < 1){
                setTabViewTextColor(current,Color.WHITE);
                binding.slidingTabLayout.setIndicatorColor(getColumnColor(current));
            }
        }

    }


    private int getColumnColor(int index){
        NewsPagerAdapter pagerAdapter = (NewsPagerAdapter) binding.newsViewPager.getAdapter();
        return Color.parseColor("#" + pagerAdapter.getColumns().get(index).getBack_color());
    }

    private void setTabViewTextColor(int index, int color){

        TextView view = binding.slidingTabLayout.getTitleView(index);
        if(view.getPaint().getColor() != color){
            Logger.d("---------------- %s 由 %s 变成 %s",view.getText().toString(),getColorString(view.getPaint().getColor()),getColorString(color));
           view.setTextColor(color);
        }

    }

    private String getColorString(int color){
        if(color == Color.WHITE){
            return "白色";
        }else if(color == Color.BLACK){
            return "黑色";
        }

        return "其他";

    }

    private void loadColumnData() {
        GetRequest<ColumnData> request = new GetRequest<>(Constrant.URL.COLUMN_MANAGER);
        request.setParams(ParamsUtils.getCommonParams());

        if (MvpUserManager.getToken() != null) {
            request.getParams().put(Constrant.RequestKey.KEY_TOKEN, MvpUserManager.getToken());
        }

        doRequest(request);


    }

    @Override
    public void onResult1(MvpResponse<ColumnData> response) {
        if (response.isOk()) {
            binding.newsViewPager.setAdapter(new NewsPagerAdapter(getChildFragmentManager(), response.getData().getList().getMyColumn()));
            binding.slidingTabLayout.setViewPager(binding.newsViewPager);
        }
    }


    private class NewsPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<ColumnData.Column> mColumns;

        public NewsPagerAdapter(@NonNull FragmentManager fm, ArrayList<ColumnData.Column> columns) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mColumns = columns;
        }


        public ArrayList<ColumnData.Column> getColumns() {
            return mColumns;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(mColumns.get(position).getId());
        }


        @Override
        public int getCount() {
            return mColumns == null ? 0 : mColumns.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mColumns.get(position).getName();

        }
    }
}
