package com.m.k.seetaoism.home;

public class Person {


    public String name;

    public int age;


    public Person(){

        name = "我还没有名字";
        age = 0;
    }

    public void println(){

        System.out.println("my name is " + name + " and  my age is " + age);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
