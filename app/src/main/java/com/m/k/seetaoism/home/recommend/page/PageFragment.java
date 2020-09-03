package com.m.k.seetaoism.home.recommend.page;

import android.graphics.Point;
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
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.data.response.ResponseType;
import com.m.k.seetaoism.data.repository.RecommendNewsRepository;
import com.m.k.seetaoism.databinding.FragmentRecommendNewsPageBinding;
import com.m.k.mvp.widgets.MvpLoadingView;
import com.m.k.seetaoism.video.SmallVideoHelper;
import com.m.k.seetaoism.video.SmallVideoPlayer;
import com.m.k.systemui.uitils.SystemFacade;
import com.m.k.video.MkVideoScrollListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class PageFragment extends BaseSmartFragment1<RecommendData> {
    private static final String KEY =  "columnId";

    private static final String PLAY_TAG_PREFIX = "PLAY_TAB_";
   private FragmentRecommendNewsPageBinding binging;

   private MergeAdapter mAdapter;
   private BannerAdapter mBannerAdapter;
   private NewsListAdapter mNewsAdapter;
   private AlbumAdapter mAlbumAdapter;


   private MvpResponse<RecommendData> response;
    private String mColumnId;

    private String name;
    private long pointTime;
    private int number;
    private int start;
    private int targetPosition;
    private int targetOffset;

    int lastVisibleItem;

    int firstVisibleItem;



    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

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
    protected int getLayoutId() {
        return R.layout.fragment_recommend_news_page;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
       binging = FragmentRecommendNewsPageBinding.bind(view);

        binging.newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });





    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LinearLayoutManager layoutManager =  ((LinearLayoutManager)binging.newsRecyclerView.getLayoutManager());
        int fistVisiblePosition =  layoutManager.findFirstVisibleItemPosition();
        View itemView = layoutManager.findViewByPosition(fistVisiblePosition);
        int y = (int) itemView.getY();

        outState.putInt("position",fistVisiblePosition);
        outState.putInt("offset",y);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate %s",name);

       // binging.newsRecyclerView.
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
        mNewsAdapter = new NewsListAdapter(makeTag());



        mAdapter.addAdapter(mBannerAdapter);
        mAdapter.addAdapter(mNewsAdapter);


        binging.newsRecyclerView.setAdapter(mAdapter);
        binging.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binging.newsRecyclerView.addOnScrollListener(new MkVideoScrollListener((LinearLayoutManager) binging.newsRecyclerView.getLayoutManager(),makeTag())/*new RecyclerView.OnScrollListener() {

            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binging.newsRecyclerView.getLayoutManager();
                firstVisibleItem   = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(makeTag())
                            && (position < firstVisibleItem || position > lastVisibleItem)) {

                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if(!GSYVideoManager.isFullState(getActivity())) {
                            GSYVideoManager.releaseAllVideos();
                            mAdapter.notifyItemChanged(position);
                        }
                    }
                }
            }
        }*/);


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


                    scrollToTargetPosition();


                if(response.getType() == ResponseType.SDCARD){
                    binging.smartRefreshLayout.autoRefresh(500);
                }

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
    public BaseSmartPresenter1<RecommendData, ?> createPresenter() {
        return new BaseSmartPresenter1<>(RecommendNewsRepository.getInstance());
    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onPause() {
        super.onPause();
        Logger.d("%s  play tag = %s  hashcode =%s",name,GSYVideoManager.instance().getPlayTag(),hashCode());
        GSYVideoManager.onPause();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       //
    }

    private String makeTag(){
        return PLAY_TAG_PREFIX + hashCode();
    }
}
