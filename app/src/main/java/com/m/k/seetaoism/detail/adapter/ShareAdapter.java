package com.m.k.seetaoism.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.ItemDetailShareBinding;

public class ShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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


    public static class ShareHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            switch (v.getId()){
                case R.id.detailPageShareSinaWeibo:{

                    break;
                }
            }
        }
    }
}
