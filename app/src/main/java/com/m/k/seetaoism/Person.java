package com.m.k.seetaoism;

public class Person implements Animal {


    public void say(){
        System.out.println("讲中文");
    }

    @Override
    public void run() {
        System.out.println("两条退走路");
    }



}
