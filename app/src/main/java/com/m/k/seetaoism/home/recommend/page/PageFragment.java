package com.m.k.seetaoism.home.recommend.page;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;

import com.m.k.GlideApp;
import com.m.k.banner.IBannerData;
import com.m.k.banner.SimpleBannerAdapter;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.widgets.MarqueeView;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.AlbumNews;
import com.m.k.seetaoism.data.entity.BannerNews;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.databinding.FragmentRecommendNewsPageBinding;
import com.m.k.seetaoism.utils.Logger;
import com.m.k.seetaoism.widgets.MvpLoadingView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends BaseSmartFragment1<RecommendData> {
    private static final String KEY =  "columnId";

   private FragmentRecommendNewsPageBinding binging;

   private MergeAdapter mAdapter;
   private BannerAdapter mBannerAdapter;
   private NewsAdapter mNewsAdapter;
   private AlbumAdapter mAlbumAdapter;


    private String mColumnId;

    private int number;
    private int start;
    private long pointTime;
    private String name;


    public static PageFragment newInstance(String columnId,String name){

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
    protected int getLayoutId() {
        return R.layout.fragment_recommend_news_page;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binging = FragmentRecommendNewsPageBinding.bind(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate %s",name);
    }


    @Override
    protected void initView() {

        binging.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refresh();
            }
        });

        mAdapter = new MergeAdapter();
        mBannerAdapter = new BannerAdapter();
        mNewsAdapter = new NewsAdapter();



        mAdapter.addAdapter(mBannerAdapter);
        mAdapter.addAdapter(mNewsAdapter);


        binging.newsRecyclerView.setAdapter(mAdapter);
        binging.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void loadData() {
        showFullLoading();
        loadData(RequestType.FIRST,start,number,pointTime);
    }

    private void refresh(){
        loadData(RequestType.REFRESH,0,0,0);
    }

    private void loadMore(){
        loadData(RequestType.LOAD_MORE,start,number,pointTime);
    }


    private void loadData(RequestType type,int start,int number,long pointTime){
        GetRequest<RecommendData> request = new GetRequest<>(Constrant.URL.RECOMMEND_LIST);
        request.putParams(Constrant.RequestKey.KEY_START,start)
                .putParams(Constrant.RequestKey.KEY_NUMBER,number)
                .putParams(Constrant.RequestKey.KEY_COLUMN_ID,mColumnId)
                .putParams(Constrant.RequestKey.KEY_POINT_TIME,pointTime);

        request.setRequestType(type);
        doRequest(request);
    }






    @Override
    public void onResult1(MvpResponse<RecommendData> response) {
        if(response.isOk()){

            if(response.getRequestType() == RequestType.FIRST){
                closeLoading();
                ArrayList<BannerAdapter.BannerWrapData> arrayList = new ArrayList<>();
                arrayList.add(new BannerAdapter.BannerWrapData(response.getData().getBannerList(),response.getData().getFlashNews()));
                mBannerAdapter.submitList(arrayList );
                mNewsAdapter.submitList(response.getData().getNews());

                start = response.getData().getStart();
                number = response.getData().getNumber();
                pointTime = response.getData().getPointTime();
            }else if(response.getRequestType() == RequestType.REFRESH){

                ArrayList<BannerAdapter.BannerWrapData> arrayList = new ArrayList<>();
                arrayList.add(new BannerAdapter.BannerWrapData(response.getData().getBannerList(),response.getData().getFlashNews()));
               mBannerAdapter.submitList(arrayList );
                mNewsAdapter.submitList(response.getData().getNews());
                start = response.getData().getStart();
                number = response.getData().getNumber();
                pointTime = response.getData().getPointTime();

                binging.smartRefreshLayout.finishRefresh();


            }else if(response.getRequestType() == RequestType.LOAD_MORE){
                mNewsAdapter.loadMore(response.getData().getNews());
                start = response.getData().getStart();
                number = response.getData().getNumber();
                pointTime = response.getData().getPointTime();
                binging.smartRefreshLayout.finishLoadMore(500);

            }


        }else{
            onError(response.getMsg(), new MvpLoadingView.OnRetryCallBack() {
                @Override
                public void onRetry() {
                    loadData();
                }
            });
        }

    }


}
