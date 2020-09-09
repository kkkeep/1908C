package com.m.k.seetaoism.detail;

import com.m.k.mvp.base.IBaseCallBack;
import com.m.k.mvp.base.p.BasePresenter;
import com.m.k.mvp.data.BaseRepository;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.request.PostRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.data.entity.Comment;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;
import com.m.k.seetaoism.data.entity.Replay;

import static com.m.k.seetaoism.Constrant.RequestKey.*;
import static com.m.k.seetaoism.Constrant.URL.*;

public class DetailPresenter extends BasePresenter<IDetailConstraint.IDetailView> implements IDetailConstraint.IDetailPresenter {


    private BaseRepository mRepository;

    public DetailPresenter() {

        mRepository = new BaseRepository();
    }

    @Override
    public void getRelatedNews(String newsId) {
        GetRequest<RelatedNewsData> request = new GetRequest<>(DETAIL_RELATIVE_NEWS);
        request.getParams().put(KEY_NEWS_ID,newsId);

        mRepository.doRequest(getLifecycleProvider(), request, new IBaseCallBack<RelatedNewsData>() {
            @Override
            public void onResult(MvpResponse<RelatedNewsData> response) {
                if(mView != null){
                    mView.onRelatedNewsResult(response);
                }
            }
        });

    }

    @Override
    public void getNewsComment(RequestType type,String newsId, long pointTime, int start) {
        GetRequest<CommentListData> request = new GetRequest<>(DETAIL_COMMENT_LIST);
        request.getParams().put(KEY_NEWS_ID_2,newsId);
        request.getParams().put(KEY_START,start);
        request.getParams().put(KEY_POINT_TIME,pointTime);
        request.setRequestType(type);

        mRepository.doRequest(getLifecycleProvider(), request, new IBaseCallBack<CommentListData>() {
            @Override
            public void onResult(MvpResponse<CommentListData> response) {
                if(mView != null){
                    mView.onNewsCommentListResult(response);
                }
            }
        });
    }

    @Override
    public void sendShareSuccess(String newsId) {
        PostRequest<String> request = new PostRequest<>(DETAIL_SHARE);
        request.getParams().put(KEY_NEWS_ID,newsId);

        mRepository.doRequest(getLifecycleProvider(), request, new IBaseCallBack<String>() {
            @Override
            public void onResult(MvpResponse<String> response) {
                if(mView != null){
                    mView.onSendShareResult(response);
                }
            }
        });
    }

    @Override
    public void sendNewsComment(String newsId, String content) {

        PostRequest<Comment> request =  new PostRequest<>(Constrant.URL.DETAIL_COMMENT_NEWS);
        request.addParams(KEY_NEWS_ID_2,newsId)
                .addParams(Constrant.RequestKey.KEY_CONTENT,content)
                .setType(Comment.class);


        mRepository.doRequest(getLifecycleProvider(), request, response -> {
            if(mView != null){
                mView.onSendNewsCommentResult(response);
            }
        });
    }

    @Override
    public void sendUserReplay(String commentId,String toId,int type,String replayId,  String newsId,String content) {

        PostRequest<Replay> request =  new PostRequest<>(DETAIL_USER_REPLAY);
        request.addParams(KEY_NEWS_ID_2,newsId)
                .addParams(Constrant.RequestKey.KEY_CONTENT,content)
                .addParams(KEY_COMMENT_ID,commentId)
                .addParams(KEY_TO_IOD,toId)
                .addParams(KEY_REPLY_TYPE,type)
                .addParams(KEY_REPLAY_ID,replayId)
                .setType(Replay.class);


        mRepository.doRequest(getLifecycleProvider(),request,response -> {
            if(mView != null){
                mView.onSendUserReplayResult(response);
            }
        });

    }


    @Override
    public void doCommentLike(String commentId) {
        PostRequest<String> request = new PostRequest<>(DETAIL_DO_COMMENT_LIKE);
        request.getParams().put(KEY_COMMENT_ID,commentId);

        mRepository.doRequest(getLifecycleProvider(), request, new IBaseCallBack<String>() {
            @Override
            public void onResult(MvpResponse<String> response) {
                if(mView != null){
                    mView.onCommentLikeResult(response);
                }
            }
        });
    }

    @Override
    public boolean cancelRequest() {
        return false;
    }
}
