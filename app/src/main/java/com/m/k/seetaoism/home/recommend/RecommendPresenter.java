package com.m.k.seetaoism.home.recommend;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.repository.RecommendRepository;
import com.m.k.seetaoism.utils.ParamsUtils;

public class RecommendPresenter implements  RecommendContract.IRecommendPresenter {

    private RecommendContract.IRecommendView mView;

    private RecommendContract.IRecommendMode mMode;


    public RecommendPresenter(){
        mMode = new RecommendRepository();
    }


    @Override
    public void bindView(RecommendContract.IRecommendView view) {
        mView = view;
    }

    @Override
    public void unBindView() {
        mView = null;
    }

    @Override
    public void getColumnData() {

        if(!isOnInternet()){
            mView.onNetError();
        }

        mView.showLoading();

        mMode.getColumnData(ParamsUtils.getCommonParams(),new IBaseCallBack<ColumnData>() {
            @Override
            public void onSuccess(ColumnData data) {
                mView.closeLoading();
                mView.onDataSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.closeLoading();
                mView.onDataFail(msg);
            }
        });

    }


    private boolean isOnInternet(){
        return true;
    }
}
