package com.m.k.seetaoism.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.MvpBaseFragment;
import com.m.k.mvp.manager.MvpFragmentManager;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.FragmentDetailBinding;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class DetailFragment extends MvpBaseFragment {

    private FragmentDetailBinding binding;

    private DetailContentFragment mContentFragment;
    @Override
    protected int getLayoutId() {

        return R.layout.fragment_detail;
    }

    @Override
    public IBasePresenter createPresenter() {
        return null;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Override
    protected void initView() {
        super.initView();

      mContentFragment =  MvpFragmentManager.addOrShowFragment(getChildFragmentManager(),DetailContentFragment.class,null,R.id.detail_content_fragment_Container,getArguments());


        binding.newsDetailShare.setOnClickListener(v -> {

            UMWeb web = new UMWeb(getArguments().getString(DetailActivity.KEY_NEW_SHARE_LINK));
            web.setTitle(getArguments().getString(DetailActivity.KEY_NEW_TITLE));//标题
            UMImage thumb = new UMImage(getActivity(),getArguments().getString(DetailActivity.KEY_NEW_IMAGE_URL));
            web.setThumb(thumb);  //缩略图
            web.setDescription(getArguments().getString(DetailActivity.KEY_NEW_DESCRIPTION));//描述

            new ShareAction(getActivity()).withMedia(web).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QQ)
                    .setCallback(new SampleShareListener() {
                        @Override
                        public void onResult(SHARE_MEDIA share_media) {
                            Logger.d();
                            mContentFragment.sendShareSuccess();
                        }

                    }).open();
        });


        binding.newsDetailBottomIv.setOnClickListener((v) -> {
            mContentFragment.showCommentPop(v);
        });
    }




    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binding = FragmentDetailBinding.bind(view);
    }



    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }
}
