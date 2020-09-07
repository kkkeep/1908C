package com.m.k.seetaoism.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.GlideApp;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.databinding.ItemDetailRelatedNewsBinding;
import com.m.k.seetaoism.detail.DetailActivity;

import java.util.ArrayList;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.NewsHolder> {

    private ArrayList<News> mNews;


    public void setNews(ArrayList<News> news) {
        this.mNews = news;
       notifyItemRangeInserted(0,mNews.size());
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_related_news,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bindData(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews == null ? 0 : mNews.size();
    }



    public class NewsHolder extends RecyclerView.ViewHolder{

        ItemDetailRelatedNewsBinding binding;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDetailRelatedNewsBinding.bind(itemView);

            itemView.setOnClickListener(v -> {
                DetailActivity.startDetailActivity(itemView.getContext(),mNews.get(getBindingAdapterPosition()));
            });
        }


        private void bindData(News news){
            binding.detailItemNewsTitle.setText(news.getTheme());
            binding.detailItemNewsLabel.setText(news.getColumn_name());
            GlideApp.with(itemView).load(news.getImageUrl()).into(binding.detailComcomtItemUserHeadPic);
        }
    }

}
