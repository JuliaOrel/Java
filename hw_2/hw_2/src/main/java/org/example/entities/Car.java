package org.example.entities;

import lombok.Data;

@Data
public class Car {
    private String name;
    private String manufacturer;
    private int year;
    private double engineVolume;

    // Конструктор без параметров
    public Car() {
    }

    // Конструктор с параметрами
    public Car(String name, String manufacturer, int year, double engineVolume) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
        this.engineVolume = engineVolume;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Year: " + year);
        System.out.println("Engine Volume: " + engineVolume + " liters");
    }
}
