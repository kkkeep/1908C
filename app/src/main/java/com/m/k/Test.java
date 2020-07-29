package com.m.k;



import com.m.k.seetaoism.Animal;
import com.m.k.seetaoism.Dog;
import com.m.k.seetaoism.Person;

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

    }



    public static void saySomething (Animal dog){
        dog.say();
    }


    public static void move(Animal dog){
       dog.run();
    }

}
