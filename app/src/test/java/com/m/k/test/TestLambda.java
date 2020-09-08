package com.m.k.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestLambda {





    public static void main(String args[]){


        printSomething("hello world", new Print() {
            @Override
            public void print(String message) {
                System.out.print(message);
            }
        });


        printSomething("hello world",(message) -> {System.out.print(message);});


        ArrayList<Integer> arrayList = new ArrayList();

        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });


        Collections.sort(arrayList);


    }

    private void test(){

        ArrayList<Integer> arrayList = new ArrayList();

        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });


    }



    public static void printSomething(String content ,Print print){

        print.print(content);
    }



    public interface Print{

        void print(String message);

    }
}
