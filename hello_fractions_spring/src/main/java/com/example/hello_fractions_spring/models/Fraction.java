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
    public void reduce() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void add(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);

        this.numerator = newNumerator;
        this.denominator = commonDenominator;

        reduce(); // Сокращаем полученную дробь
    }
}
