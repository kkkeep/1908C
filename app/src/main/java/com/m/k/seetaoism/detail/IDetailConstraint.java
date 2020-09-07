package com.m.k.seetaoism.detail;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.IBaseView;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;

public interface IDetailConstraint {



    public interface IDetailView extends IBaseView<IDetailPresenter>{


        void onRelatedNewsResult(MvpResponse<RelatedNewsData> response);
        void onNewsCommentResult(MvpResponse<CommentListData> response);

    }


     public interface IDetailPresenter extends IBasePresenter<IDetailView>{

        void getRelatedNews(String newsId);

        void getNewsComment(String newsId,long pointTime,int start);


    }


}
