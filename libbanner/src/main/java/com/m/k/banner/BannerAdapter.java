package com.m.k.banner;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BannerAdapter<T extends IBannerData,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    protected abstract ArrayList<T> getDataList();



}
