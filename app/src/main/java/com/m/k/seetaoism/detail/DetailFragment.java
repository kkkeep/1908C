package com.m.k.seetaoism.detail;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.MvpBaseFragment;
import com.m.k.seetaoism.R;

public class DetailFragment extends MvpBaseFragment {
    @Override
    protected int getLayoutId() {

        return R.layout.fragment_detail;
    }

    @Override
    public IBasePresenter createPresenter() {
        return null;
    }
}
