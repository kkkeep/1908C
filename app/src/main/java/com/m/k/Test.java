package com.m.k;



import com.google.gson.Gson;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.home.Person;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;
import io.reactivex.rxjava3.internal.operators.observable.ObservableObserveOn;
import io.reactivex.rxjava3.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test {

    public static void main(String args []) {

        Observable<String> observable1 = Observable.just("hello","zhangSan","goog","morning");
        /*Observable<String> observable2 = Observable.just("zhangSan");
        Observable<String> observable3 = Observable.just(" good ");
        Observable<String> observable4 = Observable.just(" morning");*/


      Observable<String> observable =  observable1.flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Throwable {
                System.out.println("flatMap 收到 " + s );
                return Observable.just(s);
            }
        });


        // concat 它保证有序发送数据，
        // merge 它不能保证有序
       // Observable<String> observable = Observable.merge(observable1,observable2,observable3,observable4);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("订阅成功");
            }

            @Override
            public void onNext(@NonNull String s) {

                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("订阅完成");

            }
        };


        observable.subscribe(observer);


    }


    public static void subcribleOn(){
        System.out.println(Thread.currentThread().getId()); //1

        new Thread(new Runnable() {
            @Override
            public void run() {
                // thread id = 2
                observerOn();
            }
        }).start();
    }


    public static void observerOn(){
        System.out.println(Thread.currentThread().getId()); //2

        new Thread(new Runnable() {
            @Override
            public void run() {

                // thread id = 3
                observable();
            }
        }).start();
    }


    public static void observable(){

        System.out.println(Thread.currentThread().getId()); //3

        System.out.println("11");
    }
}
