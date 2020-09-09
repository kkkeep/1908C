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
import com.m.k.mvp.data.request.PostRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.Comment;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;
import com.m.k.seetaoism.data.entity.Replay;
import com.m.k.seetaoism.databinding.FragmentDetailContentBinding;
import com.m.k.seetaoism.detail.adapter.CommentAdapter;
import com.m.k.seetaoism.detail.adapter.RelatedNewsAdapter;
import com.m.k.seetaoism.detail.adapter.ShareAdapter;
import com.m.k.systemui.uitils.SystemFacade;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DetailContentFragment  extends MvpBaseFragment<IDetailConstraint.IDetailPresenter> implements IDetailConstraint.IDetailView {


    private FragmentDetailContentBinding binding;


    private MergeAdapter mAdapter;
    private ShareAdapter mShareAdapter;
    private CommentAdapter mCommentAdapter;
    private RelatedNewsAdapter mRelatedNewsAdapter;


    private RelatedNewsData mRelatedNewsData;

    private CommentListData mCommentListData;

    private int mCurrentDoLikeCommentPosition; // 当前点赞的这条评论的position

    private String mSavedContent;

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


        mShareAdapter = new ShareAdapter(getArguments(),new SampleShareListener(){
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                sendShareSuccess();
            }

        });

        mRelatedNewsAdapter = new RelatedNewsAdapter();

        mCommentAdapter = new CommentAdapter(new CommentAdapter.OnItemClickListener() {
            @Override
            public void onReplayComment(Comment comment) {

                DetailCommentPop pop = new DetailCommentPop(getContext(), new DetailCommentPop.OnSendCallBack() {
                    @Override
                    public void onSend(String content) {
                        sendCommentRelay(comment,content);
                    }
                });
                pop.showRelay(getView(),comment.getUserName());
            }

            @Override
            public void onReplayReplay(Comment comment, String userId,Replay replay) {

                DetailCommentPop pop = new DetailCommentPop(getContext(), new DetailCommentPop.OnSendCallBack() {
                    @Override
                    public void onSend(String content) {
                        sendRelayRelay(comment,userId,replay.getReply_id(),content);
                    }
                });
                if(replay.getFromUserId().equals(userId)){
                    pop.showRelay(getView(),replay.getFromUserName());
                }else{
                    pop.showRelay(getView(),replay.getToUserName());
                }
            }

            @Override
            public void onLikeClick(String commentId,int position) {

                sendCommentLike(commentId,position);
            }
        });


        mAdapter = new MergeAdapter(mShareAdapter,mRelatedNewsAdapter,mCommentAdapter);


        binding.detailRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.detailRecyclerview.setAdapter(mAdapter);

        binding.detailWebContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        binding.detailWebContent.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mRequestCount--;
                show(RequestType.FIRST);

            }

        });

        binding.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadComment(RequestType.LOAD_MORE);
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadRelativeNews();
        loadComment(RequestType.FIRST);
    }

    /**
     * 请求相关新闻
     */
    private void loadRelativeNews(){
        mRequestCount++;
        mPresenter.getRelatedNews(mNewsId);
    }

    /**
     * 加载新闻的评论列表
     */
    private void loadComment(RequestType type){
        mRequestCount++;

        mPresenter.getNewsComment(type,mNewsId,mPointTime,mStart);
    }





    /**
     * 发送分享成功增加积分
     */
     void sendShareSuccess(){
        showPopLoading();
        mPresenter.sendShareSuccess(mNewsId);
    }


    /**
     * 显示评论输入框
     * @param view
     */
     void showCommentPop(View view){
         DetailCommentPop pop = new DetailCommentPop(getContext(), new DetailCommentPop.OnSendCallBack() {
             @Override
             public void onSend(String content) {
                 sendComment(content);
             }

             @Override
             public void onSaveContent(String content) {
                mSavedContent = content;
             }
         });

         pop.showComment(view,mSavedContent);
     }


    /**
     * 向服务器发送评论
     * @param content ，评论类容
     */
     void sendComment(String content){
         showPopLoading();
         mPresenter.sendNewsComment(mNewsId,content);


     }

    /**
     * 向服务器发送评论的回复
     * @param content
     */
     void sendCommentRelay(Comment comment,String content){
        showPopLoading();
        mPresenter.sendUserReplay(comment.getCommentId(),comment.getUserId(),1,"0",mNewsId,content);
     }

    /**
     * 向服务发送回复的回复
     * @param content
     */
     void sendRelayRelay(Comment comment,String toUserIds,String replayId,String content){

         showPopLoading();
         mPresenter.sendUserReplay(comment.getCommentId(),toUserIds,2,replayId,mNewsId,content);
     }

    /**
     * 发送评论点赞
     */
     void sendCommentLike(String commentId,int position){
         showPopLoading();
         mCurrentDoLikeCommentPosition = position;
         mPresenter.doCommentLike(commentId);

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

    // 相关新闻的回调
    @Override
    public void onRelatedNewsResult(MvpResponse<RelatedNewsData> response) {
        mRequestCount--;
        mRelatedNewsData = response.getData();
        show(RequestType.FIRST);

    }

    // 评论列表的回调
    @Override
    public void onNewsCommentListResult(MvpResponse<CommentListData> response) {
        mRequestCount--;
        mCommentListData = response.getData();
        show(response.getRequestType());
    }

    // 分享成功的增加积分的回调
    @Override
    public void onSendShareResult(MvpResponse<String> response) {
        closeLoading();
        if(response.isOk()){
            // 显示一个增加积分动画
          IntegralWidget.show(getActivity(),10);
        }
    }


    // 文章评论的回调
    @Override
    public void onSendNewsCommentResult(MvpResponse<Comment> response) {
        closeLoading();
        if(response.isOk()){
            mSavedContent = null;
            mCommentAdapter.insertComment(response.getData());
        }else{
            showToast(response.getMsg());
        }
    }

    // 回复成功回调 包含了主评论的回复和和回复的回复
    @Override
    public void onSendUserReplayResult(MvpResponse<Replay> response) {
        closeLoading();
        if(response.isOk()){
            mCommentAdapter.insertReplay(response.getData());
        }else{
            showToast(response.getMsg());
        }
    }

    // 发送点赞请求
    @Override
    public void onCommentLikeResult(MvpResponse<String> response) {
        closeLoading();

        if(response.isOk()){
            mCommentAdapter.doLike(mCurrentDoLikeCommentPosition);
        }else{
            showToast(response.getMsg());
        }

        mCurrentDoLikeCommentPosition = -1;
    }

    private void show(RequestType type){
        if(mRequestCount != 0){
            return;
        }
        closeLoading();

        if(mRelatedNewsData != null && !SystemFacade.isListEmpty(mRelatedNewsData.getNewsList())){
            mRelatedNewsAdapter.setNews(mRelatedNewsData.getNewsList());
            binding.detailRecyclerview.addItemDecoration(new CommentDecoration(mRelatedNewsData.getNewsList().size() +1));
        }


        if(mCommentListData != null && !SystemFacade.isListEmpty(mCommentListData.getCommentList())){

            sortCommentListData(mCommentListData.getCommentList());

            mPointTime = mCommentListData.getPoint_time();
            mStart = mCommentListData.getStart();

            if(type == RequestType.FIRST){

                mCommentAdapter.setComments(mCommentListData.getCommentList());

            }else if(type == RequestType.LOAD_MORE){
                mCommentAdapter.loadMore(mCommentListData.getCommentList());
                binding.smartRefreshLayout.finishLoadMore();
            }


            binding.smartRefreshLayout.setNoMoreData(mCommentListData.getMore() == 0);



        }else{
            binding.smartRefreshLayout.setNoMoreData(true);
        }


    }



    private void sortCommentListData(ArrayList<Comment> comments){

        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                int o1RelaySize  = o1.getReplyList() == null ? 0 : o1.getReplyList().size();
                int o2RelaySize  = o2.getReplyList() == null ? 0 : o2.getReplyList().size();

                if(o1RelaySize == 0 && o2RelaySize == 0){ // 都没有
                    return 0;
                }

                return   o2RelaySize - o1RelaySize;

            }
        });
    }


}
