package com.m.k.seetaoism.detail;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.IBaseView;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.data.entity.Comment;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;
import com.m.k.seetaoism.data.entity.Replay;

public interface IDetailConstraint {



    public interface IDetailView extends IBaseView<IDetailPresenter>{


        void onRelatedNewsResult(MvpResponse<RelatedNewsData> response);
        void onNewsCommentListResult(MvpResponse<CommentListData> response);
        void onSendShareResult(MvpResponse<String> response);
        void onCommentLikeResult(MvpResponse<String> response);
        void onSendNewsCommentResult(MvpResponse<Comment> response);
        void onSendUserReplayResult(MvpResponse<Replay> response);

    }


     public interface IDetailPresenter extends IBasePresenter<IDetailView>{

        void getRelatedNews(String newsId);

        void getNewsComment(RequestType type, String newsId, long pointTime, int start);

        void sendShareSuccess(String newsId);

        void sendNewsComment(String newsId,String content);

        void sendUserReplay(String commentId,String toId,int type,String replayId,String newsId,String content);

        void doCommentLike(String commentId);
    }


}
