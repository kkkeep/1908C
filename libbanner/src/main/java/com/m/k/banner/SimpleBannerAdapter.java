package com.m.k.banner;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libbanner.R;

import java.util.ArrayList;

public abstract class SimpleBannerAdapter<T extends IBannerData> extends BannerAdapter<T,SimpleBannerAdapter<T>.SimpleBannerHolder> {


    private static int mCount =0;

    private ArrayList<T> mDatas;



    public SimpleBannerAdapter(ArrayList<T> datas) {
        this.mDatas = datas;
    }



    @NonNull
    @Override
    public SimpleBannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setTag(mCount);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return new SimpleBannerHolder(imageView);
    }




    @Override
    public void onBindViewHolder(@NonNull SimpleBannerHolder holder, int position) {
        position = position % mDatas.size();

        bindData((ImageView) holder.itemView,mDatas.get(position),position);
    }

    public abstract void bindData(ImageView view,T data,int position);

    public abstract void onClick(T data,int position);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : Integer.MAX_VALUE;
    }


    @Override
    protected ArrayList<T> getDataList() {
        return mDatas;
    }

    public class SimpleBannerHolder extends RecyclerView.ViewHolder{

        public SimpleBannerHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAbsoluteAdapterPosition() % getDataList().size();
                    SimpleBannerAdapter.this.onClick(getDataList().get(index),index);
                }
            });
        }

        private void setData(int id){
            itemView.setBackgroundResource(id);
        }
    }


}
