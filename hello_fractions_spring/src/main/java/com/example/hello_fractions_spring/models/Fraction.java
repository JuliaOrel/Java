package com.example.hello_fractions_spring.models;

import lombok.Data;

@Data
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public boolean isProper() {
        return numerator < denominator;
    }
}
