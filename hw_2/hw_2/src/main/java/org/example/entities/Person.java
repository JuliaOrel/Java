package org.example.entities;

public class Person {
    private String name;
    private int age;

    public Person(){
        this.name="Unknown";
        this.age=0;
    }

    public Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public void info(){
        System.out.println("Name: "+ name);
        System.out.println("Age: "+ age);
    }
}
