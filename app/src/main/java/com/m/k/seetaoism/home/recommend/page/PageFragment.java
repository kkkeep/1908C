package com.m.k.seetaoism.home.recommend.page;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.mvp.base.p.BaseSmartPresenter1;
import com.m.k.mvp.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.data.response.ResponseType;
import com.m.k.seetaoism.data.repository.RecommendNewsRepository;
import com.m.k.seetaoism.databinding.FragmentRecommendNewsPageBinding;
import com.m.k.mvp.widgets.MvpLoadingView;
import com.m.k.seetaoism.home.NewsFragment;
import com.m.k.video.MkVideoAutoPlayScrollHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;

public class PageFragment extends NewsFragment<RecommendData> {
    private static final String KEY =  "columnId";

    private String mColumnId;
    private String name;
    public static PageFragment newInstance(String columnId, String name){

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,columnId);
        bundle.putString("name",name);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        if(args != null){
            mColumnId = args.getString(KEY);
            name = args.getString("name");
        }

    }

    @Override
    protected void loadData(RequestType type,int start,int number,long pointTime){
        GetRequest<RecommendData> request = new GetRequest<>(Constrant.URL.RECOMMEND_LIST);
        request.putParams(Constrant.RequestKey.KEY_START,start)
                .putParams(Constrant.RequestKey.KEY_NUMBER,number)
                .putParams(Constrant.RequestKey.KEY_COLUMN_ID,mColumnId)
                .putParams(Constrant.RequestKey.KEY_POINT_TIME,pointTime);

        request.setRequestType(type);
        doRequest(request);

    }

    @Override
    public BaseSmartPresenter1<RecommendData, ?> createPresenter() {
        return new BaseSmartPresenter1<>(RecommendNewsRepository.getInstance());
    }

    @Override
    public int getPageType() {
        return PAGE_TYPE_RECOMMEND;
    }
}
