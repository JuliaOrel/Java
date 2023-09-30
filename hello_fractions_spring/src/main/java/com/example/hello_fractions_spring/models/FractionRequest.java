package com.example.hello_fractions_spring.models;

import lombok.Data;


public class FractionRequest {
    private Fraction fraction1;
    private Fraction fraction2;

    // Геттеры и сеттеры для fraction1 и fraction2

    public Fraction getFraction1() {
        return fraction1;
    }

    public void setFraction1(Fraction fraction1) {
        this.fraction1 = fraction1;
    }

    public Fraction getFraction2() {
        return fraction2;
    }

    public void setFraction2(Fraction fraction2) {
        this.fraction2 = fraction2;
    }
}
