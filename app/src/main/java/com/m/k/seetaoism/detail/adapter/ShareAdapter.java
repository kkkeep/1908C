package com.m.k.seetaoism.detail.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.mvp.manager.MvpManager;
import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.ItemDetailShareBinding;
import com.m.k.seetaoism.detail.DetailActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Bundle mShareData;
    private UMShareListener mUmShareListener;

    public ShareAdapter(Bundle bundle,UMShareListener listener) {

        mShareData = bundle;

        this.mUmShareListener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ShareHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_share,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }




    public  class ShareHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemDetailShareBinding binding;


        public ShareHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDetailShareBinding.bind(itemView);

            binding.detailPageShareSinaWeibo.setOnClickListener(this);
            binding.detailPageShareWeixinCircle.setOnClickListener(this);
            binding.detailPageShareWeixin.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Activity activity = (Activity) v.getContext();

            UMWeb web = new UMWeb(mShareData.getString(DetailActivity.KEY_NEW_SHARE_LINK));
            web.setTitle(mShareData.getString(DetailActivity.KEY_NEW_TITLE));//标题
            UMImage thumb = new UMImage(activity,mShareData.getString(DetailActivity.KEY_NEW_IMAGE_URL));
            web.setThumb(thumb);  //缩略图
            web.setDescription(mShareData.getString(DetailActivity.KEY_NEW_DESCRIPTION));//描述

            SHARE_MEDIA share_media = null;
            switch (v.getId()){

                case R.id.detailPageShareSinaWeibo:{

                   share_media = SHARE_MEDIA.SINA;
                    break;
                }

                case R.id.detailPageShareWeixinCircle:{
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                }
                case R.id.detailPageShareWeixin:{
                    share_media = SHARE_MEDIA.WEIXIN;
                    break;
                }
            }
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                    .withMedia(web)
                    .setCallback(mUmShareListener)//回调监听器
                    .share();

        }
    }


}
