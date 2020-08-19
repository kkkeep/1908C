package com.m.k.seetaoism.home.recommend.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.GlideApp;
import com.m.k.banner.Banner;
import com.m.k.banner.IBannerData;
import com.m.k.banner.SimpleBannerAdapter;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.BannerNews;
import com.m.k.seetaoism.data.entity.BaseNews;
import com.m.k.seetaoism.data.entity.FlashNews;
import com.m.k.seetaoism.databinding.ItemHomeBannerBinding;
import com.m.k.seetaoism.utils.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BannerAdapter extends ListAdapter<BannerAdapter.BannerWrapData,BannerAdapter.BannerHolder> {



    protected BannerAdapter() {
       super(new DiffUtil.ItemCallback<BannerWrapData>() {
           @Override
           public boolean areItemsTheSame(@NonNull BannerWrapData oldItem, @NonNull BannerWrapData newItem) {
               return oldItem.areItemsTheSame(newItem);
           }

           @Override
           public boolean areContentsTheSame(@NonNull BannerWrapData oldItem, @NonNull BannerWrapData newItem) {
               return oldItem.areContentsTheSame(newItem);
           }
       });
    }


    @NonNull
    @Override
    public BannerAdapter.BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Logger.d("onCreateViewHolder");
        return new BannerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.BannerHolder holder, int position) {
        Logger.d("onBindViewHolder");
        holder.bindData(getCurrentList().get(0).bannerNews,getCurrentList().get(0).flashNews);
    }





    public class BannerHolder extends RecyclerView.ViewHolder{

        private ItemHomeBannerBinding binding;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemHomeBannerBinding.bind(itemView);
        }


        private void bindData(ArrayList<BannerNews> news, ArrayList<FlashNews> flashNews){

            binding.banner.setData(new SimpleBannerAdapter(news) {
                @Override
                public void bindData(ImageView view, IBannerData data) {
                    GlideApp.with(view).load(data.getImageUrl()).into(view);
                }
            });

            if(flashNews != null && flashNews.size() > 0){
                binding.flashGroup.setVisibility(View.VISIBLE);
                binding.flashView.setClickableText(flashNews);
            }else{
                binding.flashGroup.setVisibility(View.GONE);
            }
        }

    }



    public static class BannerWrapData{

        private ArrayList<BannerNews> bannerNews;
        private ArrayList<FlashNews> flashNews;

        public BannerWrapData(ArrayList<BannerNews> bannerNews, ArrayList<FlashNews> flashNews) {
            this.bannerNews = bannerNews;
            this.flashNews = flashNews;
        }

        private boolean areItemsTheSame(BannerWrapData data){
            if(bannerNews.size() != data.bannerNews.size()){
                return false;
            }

            for(int i = 0; i < bannerNews.size();i ++){
                if(!bannerNews.get(i).getId().equals(data.bannerNews.get(i).getId())){
                    return false;
                }
            }

            if(flashNews.size() != data.flashNews.size()){
                return false;
            }

            for(int i = 0; i < flashNews.size();i ++){
                if(!flashNews.get(i).getId().equals(data.flashNews.get(i).getId())){
                    return false;
                }
            }

            return true;
        }



        private boolean areContentsTheSame(BannerWrapData data){
            if(bannerNews.size() != data.bannerNews.size()){
                return false;
            }

            for(int i = 0; i < bannerNews.size();i ++){
                if(!bannerNews.get(i).getTitle().equals(data.bannerNews.get(i).getTitle())){
                    return false;
                }
            }

            if(flashNews.size() != data.flashNews.size()){
                return false;
            }

            for(int i = 0; i < flashNews.size();i ++){
                if(!flashNews.get(i).getTheme().equals(data.flashNews.get(i).getTheme())){
                    return false;
                }
            }

            return true;
        }


    }
}
