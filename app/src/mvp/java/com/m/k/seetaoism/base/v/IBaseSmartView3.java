package com.m.k.seetaoism.base.v;

import com.m.k.seetaoism.base.p.IBaseSmartPresenter3;
import com.m.k.seetaoism.data.net.response.MvpResponse;

public interface IBaseSmartView3<D1,D2,D3,P extends IBaseSmartPresenter3<D1,D2,D3,?>> extends IBaseSmartView2<D1,D2,P> {
    void onResult3(MvpResponse<D3> response);
}
