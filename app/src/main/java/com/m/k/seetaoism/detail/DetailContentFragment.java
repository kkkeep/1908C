package com.m.k.seetaoism.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.m.k.mvp.base.p.IBasePresenter;
import com.m.k.mvp.base.v.MvpBaseFragment;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.FragmentDetailContentBinding;

public class DetailContentFragment  extends MvpBaseFragment {


    private FragmentDetailContentBinding binding;

    private String contentUrl;


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        contentUrl = args.getString("url");
    }


    @Override
    protected void bindView(View view) {
        super.bindView(view);
        binding = FragmentDetailContentBinding.bind(view);


        binding.detailWebContent.loadUrl(contentUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_content;
    }

    @Override
    public IBasePresenter createPresenter() {
        return null;
    }
}
