package com.m.k.seetaoism.home.recommend.page;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;

import com.m.k.GlideApp;
import com.m.k.banner.IBannerData;
import com.m.k.banner.SimpleBannerAdapter;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.widgets.MarqueeView;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.BannerNews;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.databinding.FragmentRecommendNewsPageBinding;
import com.m.k.seetaoism.databinding.ItemHeaderBannerBinding;
import com.m.k.seetaoism.utils.Logger;
import com.m.k.seetaoism.widgets.MvpLoadingView;

import java.util.ArrayList;

public class PageFragment extends BaseSmartFragment1<RecommendData> {
    private static final String KEY =  "columnId";

   private ItemHeaderBannerBinding binging;

    private String mColumnId;

    private int number;
    private int start;
    private int pointTime;
    private String name;


    public static PageFragment newInstance(String columnId,String name){

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,columnId);
        bundle.putString("name",name);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        if(args != null){
            mColumnId = args.getString(KEY);
            name = args.getString("name");
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_header_banner;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binging = ItemHeaderBannerBinding.bind(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate %s",name);
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d("onStart %s",name);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume %s",name);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d("onPause %s",name);
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d("onStop %s",name);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

        GetRequest<RecommendData> request = new GetRequest<>(Constrant.URL.RECOMMEND_LIST);
        request.putParams(Constrant.RequestKey.KEY_START,0)
                .putParams(Constrant.RequestKey.KEY_NUMBER,0)
                .putParams(Constrant.RequestKey.KEY_POINT_TIME,0);

        showFullLoading();
        doRequest(request);
    }

    @Override
    public void onResult1(MvpResponse<RecommendData> response) {
        if(response.isOk()){
            closeLoading();

            ArrayList<BannerNews> bannerNews = response.getData().getBannerList();

            binging.banner.setLifecycleOwner(this);
            binging.banner.setData(new SimpleBannerAdapter(bannerNews) {
                @Override
                public void bindData(ImageView view, IBannerData data) {
                    GlideApp.with(view).load(data.getImageUrl()).into(view);
                }
            });



            binging.flashView.setClickableText(response.getData().getFlashNews());
            binging.flashView.setOnMarqueeTextClickListener(new MarqueeView.OnMarqueeTextClickListener<MarqueeView.MarqueeData>() {
                @Override
                public void onClick(MarqueeView.MarqueeData data, int position) {
                        showToast(response.getData().getFlashNews().get(position).getTheme());
                }
            });

        }else{
            onError(response.getMsg(), new MvpLoadingView.OnRetryCallBack() {
                @Override
                public void onRetry() {
                    loadData();
                }
            });
        }

    }




}
