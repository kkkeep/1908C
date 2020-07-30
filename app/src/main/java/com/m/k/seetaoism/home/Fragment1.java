package com.m.k.seetaoism.home;

import android.view.View;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginPresenter;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.base.v.MvpBaseFragment;
import com.m.k.seetaoism.data.entity.ColumnData;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.GetRequest;
import com.m.k.seetaoism.data.net.request.MvpRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.manager.MvpFragmentManager;
import com.m.k.seetaoism.utils.ParamsUtils;

public class Fragment1 extends BaseSmartFragment1<ColumnData> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }


    @Override
    protected void initView() {
        findViewById(R.id.fragment1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MvpFragmentManager.addOrShowFragment(getFragmentManager(),Fragment2.class,Fragment1.this,R.id.home_fragment_container);
                doRequest(getRequest());
                showPopLoading(getRequest().isEnableCancel());
            }
        });


    }

    @Override
    public int getEnterAnimation() {
        return 0;
    }

    @Override
    public int getPopExitAnimation() {
        return 0;
    }

    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }


    @Override
    public void onResult1(MvpResponse<ColumnData> response) {
        showToast("成功");
        closeLoading();
    }

    public MvpRequest<ColumnData> getRequest() {
        GetRequest<ColumnData> request = new GetRequest<>("/api/column/columnmanagelist");
        request.setParams(ParamsUtils.getCommonParams());
        request.setEnableCancel(true);
        return request;
    }
}
