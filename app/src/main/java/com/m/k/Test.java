package com.m.k;



import com.m.k.seetaoism.Animal;
import com.m.k.seetaoism.Dog;
import com.m.k.seetaoism.Person;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class Test {

    public static void main(String args []) throws InterruptedException {

       ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

       new Thread(new Runnable() {
           @Override
           public void run() {
                System.out.println("thread1 put 1 into thread local");
               threadLocal.set(1);
               System.out.println("thread1 get value from thread local " + threadLocal.get());
           }
       }).start();






       Thread.sleep(1000);


        System.out.println("main thread get value from thread local " + threadLocal.get());

    }



    public static void saySomething (Animal dog){
        dog.say();
    }


    public static void move(Animal dog){
       dog.run();
    }

}
