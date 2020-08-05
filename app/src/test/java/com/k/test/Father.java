package com.k.test;

public class Father {

    protected int money;



    public Father(int money) {
        this.money = money;
    }

    protected void doSomething(){
        sleep();
    }


    protected void play(){
        System.out.println("玩游戏");
    }


    private void sleep(){
        System.out.println("你爸和你妈一起睡觉");
    }
}
