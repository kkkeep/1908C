package com.m.k.seetaoism.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.mvp.base.p.BaseSmartPresenter1;
import com.m.k.mvp.base.v.BaseSmartFragment1;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.data.response.ResponseType;
import com.m.k.mvp.utils.Logger;
import com.m.k.mvp.widgets.MvpLoadingView;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.NewsData;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.seetaoism.data.repository.RecommendNewsRepository;
import com.m.k.seetaoism.databinding.FragmentRecommendNewsPageBinding;
import com.m.k.seetaoism.home.recommend.page.AlbumAdapter;
import com.m.k.seetaoism.home.recommend.page.BannerAdapter;
import com.m.k.seetaoism.home.recommend.page.NewsListAdapter;
import com.m.k.systemui.uitils.SystemFacade;
import com.m.k.video.MkVideoAutoPlayScrollHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;

public abstract class NewsFragment<T extends NewsData> extends BaseSmartFragment1<T> {



   private static final String PLAY_TAG_PREFIX = "PLAY_TAB_";

   public static final  int PAGE_TYPE_RECOMMEND = 0x0; // 推荐页
    public static final  int PAGE_TYPE_VIDEO = 0x1; // 视屏页
    public static final  int PAGE_TYPE_SPECIAL = 0x2; // 专业题

   private FragmentRecommendNewsPageBinding binging;

   private MergeAdapter mAdapter;
   private BannerAdapter mBannerAdapter;
   private NewsListAdapter mNewsAdapter;
   private AlbumAdapter mAlbumAdapter;


   private MvpResponse<T> response;


    private long pointTime;
    private int number;
    private int start;
    private int targetPosition;
    private int targetOffset;
    private int more;




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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LinearLayoutManager layoutManager =  ((LinearLayoutManager)binging.newsRecyclerView.getLayoutManager());
        int fistVisiblePosition =  layoutManager.findFirstVisibleItemPosition();

        Logger.d("fistVisiblePosition = %s",fistVisiblePosition);
        View itemView = layoutManager.findViewByPosition(fistVisiblePosition);
        if(itemView == null){
            return;
        }
        int y = (int) itemView.getY();

        outState.putInt("position",fistVisiblePosition);
        outState.putInt("offset",y);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            targetPosition = savedInstanceState.getInt("position");
            targetOffset = savedInstanceState.getInt("offset");
        }
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
        mBannerAdapter = new BannerAdapter(this);
        mNewsAdapter = new NewsListAdapter(makeTag(),getPageType());



        mAdapter.addAdapter(mBannerAdapter);
        mAdapter.addAdapter(mNewsAdapter);


        binging.newsRecyclerView.setAdapter(mAdapter);
        binging.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binging.newsRecyclerView.addOnScrollListener(new MkVideoAutoPlayScrollHelper((LinearLayoutManager) binging.newsRecyclerView.getLayoutManager()));

        loadData();

    }

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


    protected abstract void loadData(RequestType type,int start,int number,long pointTime);







    @Override
    public void onResult1(MvpResponse<T> response) {

        if(response.isOk()){
            start = response.getData().getStart();
            number = response.getData().getNumber();
            pointTime = response.getData().getPointTime();
            more = response.getData().getMore();

            if(response.getRequestType() == RequestType.FIRST){
                closeLoading();
                if(!SystemFacade.isListEmpty(response.getData().getBannerList())){
                    ArrayList<BannerAdapter.BannerWrapData> arrayList = new ArrayList<>();
                    arrayList.add(new BannerAdapter.BannerWrapData(response.getData().getBannerList(),response.getData().getFlashNews()));
                    mBannerAdapter.submitList(arrayList );
                }

                mNewsAdapter.submitList(response.getData().getNewsList());

                scrollToTargetPosition();


                if(response.getType() == ResponseType.SDCARD){
                    binging.smartRefreshLayout.autoRefresh(500);
                }else{

                    MkVideoAutoPlayScrollHelper.playIfNeed(binging.newsRecyclerView);

                }

            }else if(response.getRequestType() == RequestType.REFRESH){

                if(!SystemFacade.isListEmpty(response.getData().getBannerList())){
                    ArrayList<BannerAdapter.BannerWrapData> arrayList = new ArrayList<>();
                    arrayList.add(new BannerAdapter.BannerWrapData(response.getData().getBannerList(),response.getData().getFlashNews()));
                    mBannerAdapter.submitList(arrayList );
                }
                mNewsAdapter.submitList(response.getData().getNewsList());

                binging.smartRefreshLayout.finishRefresh();

                if(isResumed()){
                    MkVideoAutoPlayScrollHelper.playIfNeed(binging.newsRecyclerView);
                }

            }else if(response.getRequestType() == RequestType.LOAD_MORE){

                mNewsAdapter.loadMore(response.getData().getNewsList());
                binging.smartRefreshLayout.finishLoadMore(500);
            }


            if(more == 0){ // 没有更多了
                binging.smartRefreshLayout.setNoMoreData(true);
            }

        }else{

            if(response.getRequestType() == RequestType.FIRST){
                onError(response.getMsg(), new MvpLoadingView.OnRetryCallBack() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }
                });
            }else if(response.getRequestType() == RequestType.REFRESH){
                binging.smartRefreshLayout.finishRefresh();
                showToast("刷新失败");
            }else if(response.getRequestType() == RequestType.LOAD_MORE){
                binging.smartRefreshLayout.finishLoadMore(500);
                showToast("加载更多失败");
            }

        }

    }

    private void scrollToTargetPosition() {

        binging.newsRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager = (LinearLayoutManager) binging.newsRecyclerView.getLayoutManager();
                int position = layoutManager.findFirstVisibleItemPosition();

                if(position != targetPosition){
                    layoutManager.scrollToPositionWithOffset(targetPosition,targetOffset);
                }

            }
        });
    }




    @Override
    public void onResume() {
        super.onResume();

        MkVideoAutoPlayScrollHelper.playIfNeed(binging.newsRecyclerView);
    }


    @Override
    public void onPause() {
        super.onPause();
        Logger.d("%s  play tag = %s  hashcode =%s","",GSYVideoManager.instance().getPlayTag(),hashCode());

        if(GSYVideoManager.instance().getPlayPosition() > 0 && GSYVideoManager.instance().getPlayTag().equals(makeTag())){
            GSYVideoManager.onPause();
        }



    }
    private String makeTag(){
        return PLAY_TAG_PREFIX + hashCode();
    }

    public abstract int getPageType();
}
