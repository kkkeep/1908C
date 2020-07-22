package com.m.k.seetaoism.data.repository;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.BaseRepository;
import com.m.k.seetaoism.data.entity.Banner;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.net.ok.DataService;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecommendRepository extends BaseRepository {



    public void getBanner(String url, HashMap<String,String> params, IBaseCallBack<Banner> callBack){

    }


}
