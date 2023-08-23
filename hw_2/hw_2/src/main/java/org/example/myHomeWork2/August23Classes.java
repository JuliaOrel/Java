package org.example.myHomeWork2;

import org.example.entities.Book;
import org.example.entities.Car;
import org.example.entities.Fraction;
import org.example.entities.Person;
import org.example.exceptions.DivisionByZeroValidation;

public class August23Classes implements Runnable {

    @Override
    public void run() {

        //Person
//        Person person1=new Person();
//        person1.info();
//
//        Person person2=new Person("John", 17);
//        person2.info();
//
//        person1.setName("Greg");
//        person1.setAge(34);
//        person1.info();

        //Fraction
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
        System.out.println();

        //Book
//        Book book1 = new Book();
//        book1.setTitle("The Grand Chessboard");
//        book1.setAuthor("Zbigniew Brzezinski.");
//        book1.setYear(1997);
//        book1.setPublisher("Basic Books");
//        book1.setGenre("\tGeopolitics");
//        book1.setPageCount(223);
//
//        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "HarperCollins", "Fiction", 324);
//
//        book1.displayInfo();
//        System.out.println();
//        book2.displayInfo();

        //Car
        Car car1 = new Car();
        car1.setName("MINI John Cooper Works");
        car1.setManufacturer("BMW");
        car1.setYear(2019);
        car1.setEngineVolume(7.1);

        Car car2 = new Car("Honda Civic", "Honda", 2021, 1.5);

        car1.displayInfo();
        System.out.println();
        car2.displayInfo();
    }
}
