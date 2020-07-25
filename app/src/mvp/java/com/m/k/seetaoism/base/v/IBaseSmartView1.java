package com.m.k.seetaoism.base.v;

import com.m.k.seetaoism.base.p.IBaseSmartPresenter1;
import com.m.k.seetaoism.data.net.response.MvpResponse;

public interface IBaseSmartView1<D,P extends IBaseSmartPresenter1> extends IBaseView<P> {
    void onResult1(MvpResponse<D> response);
}
