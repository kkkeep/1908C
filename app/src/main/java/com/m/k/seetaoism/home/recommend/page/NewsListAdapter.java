package com.m.k.seetaoism.home.recommend.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.GlideApp;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.databinding.ItemNewsFlashBinding;
import com.m.k.seetaoism.databinding.ItemNewsNewsLeftBinding;
import com.m.k.seetaoism.databinding.ItemNewsNewsRightBinding;
import com.m.k.seetaoism.databinding.ItemNewsSpecialBinding;
import com.m.k.seetaoism.databinding.ItemNewsVideoBinding;
import com.m.k.seetaoism.utils.Logger;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends ListAdapter<News, NewsListAdapter.NewsHolder> {


    private static final int TYPE_LEFT_PIC = 0x1; // 左图
    private static final int TYPE_RIGHT_PIC = 0x3; // right pic
    private static final int TYPE_FlASH_PIC = 0x5; // only text not pic align left and right
    private static final int TYPE_VIDEO_PIC = 0x4; // video
    private static final int TYPE_SPECIAL_PIC = 0x2; //  big pic

    public NewsListAdapter() {
        super(new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getTheme().equals(newItem.getTheme());
            }
        });
    }




    public void loadMore(ArrayList<News> moreData){
        List<News> news = new ArrayList<>();
        news.addAll(getCurrentList());
        Logger.d("------- 原来的size = %s",news.size() );
        news.addAll(moreData);

        Logger.d("------- 后来的的size = %s",news.size() );
        submitList(news);
    }



    @NonNull
    @Override
    public NewsListAdapter.NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Logger.d();

        int layoutId;
        Class<? extends NewsHolder> aClass;
        switch (viewType){
            case TYPE_RIGHT_PIC:{
                layoutId = R.layout.item_news_news_right;
                aClass = RightHolder.class;
                break;

            }
            case TYPE_SPECIAL_PIC:{
                layoutId = R.layout.item_news_special;
                aClass = BigHolder.class;
                break;
            }

            case TYPE_FlASH_PIC:{
                layoutId = R.layout.item_news_flash;
                aClass = FlashHolder.class;
                break;
            }
            case TYPE_VIDEO_PIC:{
                layoutId = R.layout.item_news_video;
                aClass = VideoHolder.class;
                break;
            }
            default:{
                layoutId = R.layout.item_news_news_left;
                aClass = LeftHolder.class;
                break;
            }
        }


        try {
            Constructor<? extends NewsHolder> constructor = aClass.getConstructor(NewsListAdapter.class,View.class);
            return constructor.newInstance(this,LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void submitList(@Nullable List<News> list) {
        super.submitList(list);
        Logger.d("------- 原来的size = %s",getItemCount() );
    }



    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.NewsHolder holder, int position) {
        Logger.d();

        holder.bindData(getCurrentList().get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return getCurrentList().get(position).getView_type();
    }
/*
    @Override
    public int getItemCount() {
        return news == null ? 0 : news.size();
    }*/


    public  class NewsHolder extends RecyclerView.ViewHolder{

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(News news){

        }
    }


    private  class LeftHolder extends NewsHolder{

        private ItemNewsNewsLeftBinding binding;

        public LeftHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsNewsLeftBinding.bind(itemView);
        }

        @Override
        public void bindData(News news) {
            binding.title.setText(news.getTheme());
            GlideApp.with(itemView).load(news.getImageUrl()).into(binding.newsPic);
            binding.label.setText(news.getColumn_name());
        }
    }

    private   class RightHolder extends NewsHolder{

        private ItemNewsNewsRightBinding binding;
        public RightHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsNewsRightBinding.bind(itemView);
        }

        @Override
        public void bindData(News news) {
            binding.title.setText(news.getTheme());
            GlideApp.with(itemView).load(news.getImageUrl()).into(binding.newsPic);
            binding.label.setText(news.getColumn_name());
        }
    }

    private  class BigHolder extends NewsHolder{

        private ItemNewsSpecialBinding binding;
        public BigHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsSpecialBinding.bind(itemView);
        }

        @Override
        public void bindData(News news) {
            binding.title.setText(news.getTheme());
            GlideApp.with(itemView).load(news.getImageUrl()).into(binding.newsPic);
            binding.label.setText(news.getColumn_name());
        }
    }

    private  class FlashHolder extends NewsHolder{

        private ItemNewsFlashBinding binding;
        public FlashHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsFlashBinding.bind(itemView);
        }

        @Override
        public void bindData(News news) {
            binding.title.setText(news.getTheme());
            binding.content.setText(news.getContent());
            binding.time.setText(news.getEdit_time());

        }
    }


    private   class VideoHolder extends NewsHolder{

        private ItemNewsVideoBinding binding;
        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsVideoBinding.bind(itemView);
        }

        @Override
        public void bindData(News news) {
            binding.title.setText(news.getTheme());
            GlideApp.with(itemView).load(news.getImageUrl()).into(binding.cover);
            binding.label.setText(news.getColumn_name());
        }
    }


}
