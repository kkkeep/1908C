package com.m.k.seetaoism.home.recommend.page;

import android.os.Bundle;

import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.response.MvpResponse;

public class PageFragment extends BaseSmartFragment1<User> {
    private static final String KEY =  "columnId";

    private String mColumnId;

    public static PageFragment newInstance(String columnId){

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,columnId);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        if(args != null){
            mColumnId = args.getString(KEY);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }

    @Override
    public void onResult1(MvpResponse response) {

    }


}
