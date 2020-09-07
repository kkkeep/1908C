package com.m.k.seetaoism.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.MergeAdapter;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.MvpBaseFragment;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;
import com.m.k.seetaoism.databinding.FragmentDetailContentBinding;
import com.m.k.seetaoism.detail.adapter.CommentAdapter;
import com.m.k.seetaoism.detail.adapter.RelatedNewsAdapter;
import com.m.k.seetaoism.detail.adapter.ShareAdapter;
import com.m.k.systemui.uitils.SystemFacade;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

public class DetailContentFragment  extends MvpBaseFragment<IDetailConstraint.IDetailPresenter> implements IDetailConstraint.IDetailView {


    private FragmentDetailContentBinding binding;


    private MergeAdapter mAdapter;
    private ShareAdapter mShareAdapter;
    private CommentAdapter mCommentAdapter;
    private RelatedNewsAdapter mRelatedNewsAdapter;


    private RelatedNewsData mRelatedNewsData;

    private CommentListData mCommentListData;

    private String contentUrl;

    private String mNewsId;

    private int mRequestCount;


    private int mStart;
    private long mPointTime;
    private int mNumber;


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args != null){
            contentUrl = args.getString(DetailActivity.KEY_URL);
            mNewsId = args.getString(DetailActivity.KEY_NEW_ID);
        }
    }


    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binding = FragmentDetailContentBinding.bind(view);


        binding.detailWebContent.loadUrl(contentUrl);
        mRequestCount++;

        showFullLoading();
    }


    @Override
    protected void initView() {
        super.initView();

        binding.smartRefreshLayout.setEnableRefresh(false);
        binding.detailRecyclerview.setNestedScrollingEnabled(false);


        mShareAdapter = new ShareAdapter();
        mRelatedNewsAdapter = new RelatedNewsAdapter();
        mCommentAdapter = new CommentAdapter();
        mAdapter = new MergeAdapter(mShareAdapter,mRelatedNewsAdapter,mCommentAdapter);


        binding.detailRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.detailRecyclerview.setAdapter(mAdapter);

        binding.detailWebContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Logger.d("newProgeress = %s",newProgress );
            }
        });

        binding.detailWebContent.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logger.d("shouldOverrideUrlLoading = %s",url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Logger.d("onPage finish");
                mRequestCount--;
                show();

            }

        });

        binding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                showToast("准备加载更多");
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadRelativeNews();
        loadComment();
    }

    private void loadRelativeNews(){


        mRequestCount++;

        mPresenter.getRelatedNews(mNewsId);
    }

    private void loadComment(){
        mRequestCount++;

        mPresenter.getNewsComment(mNewsId,mPointTime,mStart);
    }




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_content;
    }

    @Override
    public IDetailConstraint.IDetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }

    @Override
    public void onRelatedNewsResult(MvpResponse<RelatedNewsData> response) {
        mRequestCount--;
        mRelatedNewsData = response.getData();
        show();

    }

    @Override
    public void onNewsCommentResult(MvpResponse<CommentListData> response) {
        mRequestCount--;
        mCommentListData = response.getData();
        show();
    }

    private void show(){
        if(mRequestCount != 0){
            return;
        }
        closeLoading();

        if(mRelatedNewsData != null && !SystemFacade.isListEmpty(mRelatedNewsData.getNewsList())){
            mRelatedNewsAdapter.setNews(mRelatedNewsData.getNewsList());
        }


        if(mCommentListData != null && !SystemFacade.isListEmpty(mCommentListData.getCommentList())){

            mCommentAdapter.setComments(mCommentListData.getCommentList());

            binding.smartRefreshLayout.setNoMoreData(mCommentListData.getMore() == 0);

        }


    }


}
