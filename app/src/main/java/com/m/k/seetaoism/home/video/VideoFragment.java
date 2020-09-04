package com.m.k.seetaoism.home.video;

import android.os.Bundle;

import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.seetaoism.data.entity.VideoData;
import com.m.k.seetaoism.home.NewsFragment;
import com.m.k.seetaoism.home.recommend.page.PageFragment;

public class VideoFragment extends NewsFragment<VideoData> {


    @Override
    protected void loadData(RequestType type, int start, int number, long pointTime) {
        GetRequest<VideoData> request = new GetRequest<>(Constrant.URL.VIDEO_LIST);
        request.putParams(Constrant.RequestKey.KEY_START,start)
                .putParams(Constrant.RequestKey.KEY_NUMBER,number)
                .putParams(Constrant.RequestKey.KEY_POINT_TIME,pointTime);
        request.setRequestType(type);
        doRequest(request);

    }


    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }


    @Override
    public boolean isNeedAnimation() {
        return false;
    }


    @Override
    public int getPageType() {
        return PAGE_TYPE_VIDEO;
    }
}
