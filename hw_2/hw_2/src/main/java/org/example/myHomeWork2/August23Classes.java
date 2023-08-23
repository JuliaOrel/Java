package org.example.myHomeWork2;

import org.example.entities.Person;

public class August23Classes implements Runnable {

    @Override
    public void run() {
        Person person1=new Person();
        person1.info();

        Person person2=new Person("John", 17);
        person2.info();

        person1.setName("Greg");
        person1.setAge(34);
        person1.info();
    }
}
