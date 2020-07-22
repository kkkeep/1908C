package com.m.k.seetaoism.data;

import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.HttpResult;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class BaseRepository {
    // UserResult<User>
    public <T> void doObserver(@NonNull Observable<HttpResult<T>> observable, @NonNull Consumer<T> consumer, IBaseCallBack<T> callBack) {
        observable.map(new Function<HttpResult<T>, T>() {

            @Override
            public T apply(HttpResult<T> result) throws Throwable {
                if (result.getCode() == 1) {
                    if (result.getData() != null) {
                        return result.getData();
                    } else {
                        throw new Exception("服务器异常");
                    }
                } else {
                    throw new Exception("服务器异常");
                }
            }
        }).doOnNext(consumer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T result) {
                        if(callBack != null){
                            callBack.onSuccess(result);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if(callBack != null){
                            callBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public <T> void doObserver(Observable<HttpResult<T>> observable, IBaseCallBack<T> callBack) {

        doObserver(observable, new Consumer<T>() {
            @Override
            public void accept(T t) throws Throwable {

            }
        }, callBack);
    }











   /* public <T> void doPost(String url, HashMap<String, String> params, IBaseCallBack<T> callBack) {


        DataService.getService().doPost(url, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        //1 如何把json 串转成 T 对应的实际类型
                        // 2. onNext 是在主线程，我们这种转json 串是一个比较耗时的操作，肯定会对UI 的流畅度有影响。

                       // callBack.onSuccess(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                     callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/
}
