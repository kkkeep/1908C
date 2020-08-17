package com.m.k.seetaoism.home.recommend;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.mvp.utils.MvpUtils;
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
import java.util.List;

public class RecommendFragment  extends BaseSmartFragment1<ColumnData> {

    FragmentRecommendBinding binding;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }


    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binding = FragmentRecommendBinding.bind(view);


        binding.newsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.d("%s position = %s positionOffset = %s positionOffsetPixels = %s,current = %s", "RecommendFragment", position, positionOffset, positionOffsetPixels,binding.newsViewPager.getCurrentItem());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        loadColumnData();
    }


    private void changeTabColor(int position,int offset){


        int currentItem = binding.newsViewPager.getCurrentItem();

        int nextPosition;

        if(currentItem > position){ // 往回滑动

        }

    }

    private void loadColumnData(){
        GetRequest<ColumnData> request = new GetRequest<>(Constrant.URL.COLUMN_MANAGER);
        request.setParams(ParamsUtils.getCommonParams());

        if(MvpUserManager.getToken() != null){
            request.getParams().put(Constrant.RequestKey.KEY_TOKEN, MvpUserManager.getToken());
        }

        doRequest(request);


    }

    @Override
    public void onResult1(MvpResponse<ColumnData> response) {
        if(response.isOk()){
            binding.newsViewPager.setAdapter(new NewsPagerAdapter(getChildFragmentManager(),response.getData().getList().getMyColumn()));
            binding.slidingTabLayout.setViewPager(binding.newsViewPager);
        }
    }





    private class NewsPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<ColumnData.Column> mColumns;

        public NewsPagerAdapter(@NonNull FragmentManager fm, ArrayList<ColumnData.Column> columns) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mColumns = columns;
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
