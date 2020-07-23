package com.m.k.seetaoism.home.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.utils.Logger;

import java.util.Arrays;

public class RecommendFragment extends Fragment implements RecommendContract.IRecommendView {

    private static final String TAG = "RecommendFragment";

    private RecommendContract.IRecommendPresenter mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RecommendPresenter();
        mPresenter.bindView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.home_fragment_recommend,container,false);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.getColumnData();
    }

    @Override
    public void onDataSuccess(ColumnData data) {
        Logger.d("%s get column data success %s",TAG,Arrays.toString(data.getList().getMyColumn().toArray()));
        Toast.makeText(getContext(),"数据回来了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataFail(String msg) {
        Logger.d("get column data fail");
    }

    @Override
    public void onNetError() {
        //
    }

    @Override
    public void showLoading() {
        //
    }

    @Override
    public void closeLoading() {
//
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
    }
}
