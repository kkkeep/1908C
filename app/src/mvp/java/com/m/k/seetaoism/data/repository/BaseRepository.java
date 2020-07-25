package com.m.k.seetaoism.data.repository;

import com.google.gson.Gson;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.base.m.IBaseMode;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.net.request.MvpRequest;
import com.m.k.seetaoism.data.net.ok.DataService;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.utils.ParameterizedTypeImpl;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class BaseRepository  implements IBaseMode {

    @SuppressWarnings("ALL")
    public Consumer empty = o -> { };
    @SuppressWarnings("ALL")
    @Override
       public <T> void doRequest(MvpRequest<T> request, IBaseCallBack<T> callBack) {
        doRequest(request, empty, callBack);
    }

    public <T> void doRequest(MvpRequest<T> request, Consumer<MvpResponse<T>> doBackground, IBaseCallBack<T> callBack) {

        switch (request.getRequestMethod()) {
            case GET: {
                doObserver(request, DataService.getService().doGet(request.getUrl(), request.getHeaders(), request.getParams()), doBackground, callBack);
                break;
            }
            case POST: {
                doObserver(request, DataService.getService().doPost(request.getUrl(), request.getHeaders(), request.getParams()), doBackground, callBack);
                break;
            }
        }
    }

    protected  <T> void doObserver(MvpRequest<T> request, Observable<String> observable, Consumer<MvpResponse<T>> consumer, IBaseCallBack<T> callBack) {
        observable.map(json2Data(request))
                .doOnNext(consumer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MvpResponse<T>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if(request.isEnableCancel()){
                            callBack.onStart(d);
                        }
                    }

                    @Override
                    public void onNext(@NonNull MvpResponse<T> data) {

                        callBack.onResult(data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onResult(new MvpResponse<T>().message(e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public <T> Function<String, MvpResponse<T>> json2Data(MvpRequest<T> request) {
        return new Function<String, MvpResponse<T>>() {
            @Override
            public MvpResponse<T> apply(String s) throws Throwable {
                // IBaseCallBack<ColumnData>

                /*Type[] types = callBack.getClass().getGenericInterfaces();
                ParameterizedType parameterizedType = (ParameterizedType) types[0];
                Type realType = parameterizedType.getActualTypeArguments()[0];
                */

                //HttpResult<ColumnData>
                Gson gson = new Gson();
                ParameterizedTypeImpl type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{request.getType()});
                HttpResult<T> data = gson.fromJson(s, type);
                if (data.getCode() == 1) {
                    if (data.getData() != null) {
                        return new MvpResponse<T>().setData(data.getData()).setCode(data.getCode());
                    } else {
                        return new MvpResponse<T>().setCode(data.getCode()).message("服务器异常");
                    }
                } else {
                    return new MvpResponse<T>().setCode(data.getCode()).message(data.getMessage());
                }
            }
        };
    }

}
