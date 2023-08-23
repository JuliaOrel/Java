package org.example.myHomeWork2;

import org.example.entities.Fraction;
import org.example.entities.Person;
import org.example.exceptions.DivisionByZeroValidation;

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


        Fraction fraction=new Fraction();
        System.out.println("Original fraction: "+ fraction);

        try {
            fraction.setFraction(3,5);
        } catch (DivisionByZeroValidation e) {
            System.out.println("You can't divide by 0");
        }
        System.out.println("Updated fraction: "+ fraction);

        fraction.setFraction(4);
        System.out.println("Updated fraction: "+ fraction);
    }
}
