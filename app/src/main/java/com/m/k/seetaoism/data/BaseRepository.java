package com.m.k.seetaoism.data;

import com.google.gson.Gson;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.base.IBaseMode;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.net.MvpRequest;
import com.m.k.seetaoism.data.net.ok.DataService;
import com.m.k.seetaoism.utils.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
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
       public <T> void doRequest(MvpRequest request, IBaseCallBack<T> callBack) {
        doRequest(request, empty, callBack);
    }

    public <T> void doRequest(MvpRequest request, Consumer<T> doBackground, IBaseCallBack<T> callBack) {

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

    protected  <T> void doObserver(MvpRequest request, Observable<String> observable, Consumer<T> consumer, IBaseCallBack<T> callBack) {
        observable.map(json2Data(callBack))
                .doOnNext(consumer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T data) {
                        callBack.onSuccess(data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public <T> Function<String, T> json2Data(IBaseCallBack<T> callBack) {
        return new Function<String, T>() {
            @Override
            public T apply(String s) throws Throwable {
                // IBaseCallBack<ColumnData>

                Type[] types = callBack.getClass().getGenericInterfaces();
                ParameterizedType parameterizedType = (ParameterizedType) types[0];
                Type realType = parameterizedType.getActualTypeArguments()[0];
                Gson gson = new Gson();

                //HttpResult<ColumnData>
                ParameterizedTypeImpl type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{realType});

                HttpResult<T> data = gson.fromJson(s, type);
                if (data.getCode() == 1) {
                    if (data.getData() != null) {
                        return data.getData();
                    } else {
                        throw new Exception("服务器异常");
                    }
                } else {
                    throw new Exception(data.getMessage());
                }
            }
        };
    }

}
