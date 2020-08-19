package com.k.test;

import androidx.annotation.Nullable;

public class Person {


    private String id;
    private String name;


    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if(obj == null){
            return false;
        }
        if(!(obj instanceof Person)){
            return false;
        }

        Person person = (Person) obj;
        if(person.getId().equals(this.getId())){
            return true;
        }

        return false;

    }
}
