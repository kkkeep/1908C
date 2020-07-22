package com.m.k.seetaoism.home.recommend;

import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.entity.User;

import java.util.HashMap;

public interface RecommendContract {

    public interface IRecommendView{

        void onDataSuccess(ColumnData data);
        void onDataFail(String msg);

        void onNetError();
        void showLoading();
        void closeLoading();
    }

    public interface IRecommendPresenter{
        void bindView(IRecommendView view);
        void unBindView();
        void getColumnData();
    }
    public interface IRecommendMode{
        void getColumnData(HashMap<String,String> params,IBaseCallBack<ColumnData> callBack);
    }
}
