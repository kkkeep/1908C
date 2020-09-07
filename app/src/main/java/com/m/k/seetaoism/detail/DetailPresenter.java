package com.m.k.seetaoism.detail;

import com.m.k.mvp.base.IBaseCallBack;
import com.m.k.mvp.base.p.BasePresenter;
import com.m.k.mvp.data.BaseRepository;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.data.entity.CommentListData;
import com.m.k.seetaoism.data.entity.RelatedNewsData;

import io.reactivex.rxjava3.functions.Consumer;

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
    public void getNewsComment(String newsId, long pointTime, int start) {
        GetRequest<CommentListData> request = new GetRequest<>(DETAIL_COMMENT_LIST);
        request.getParams().put(KEY_NEWS_ID_2,newsId);
        request.getParams().put(KEY_START,start);
        request.getParams().put(KEY_POINT_TIME,pointTime);

        mRepository.doRequest(getLifecycleProvider(), request, new IBaseCallBack<CommentListData>() {
            @Override
            public void onResult(MvpResponse<CommentListData> response) {
                if(mView != null){
                    mView.onNewsCommentResult(response);
                }
            }
        });
    }

    @Override
    public boolean cancelRequest() {
        return false;
    }
}
