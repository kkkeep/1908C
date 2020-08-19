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

    public static void main(String args []) {

        Animal dog = new Person();

        saySomething(dog);
        move(dog);

        DecimalFormat df = new DecimalFormat("000");

        String str2 = df.format(20);

        System.out.println(str2);

       //System.out.println(String.format("%03",1));
       //System.out.println(String.format("%03",23));



        // mMaskView.setBackgroundResource();


    }



    public static void saySomething (Animal dog){
        dog.say();
    }


    public static void move(Animal dog){
       dog.run();
    }

}
