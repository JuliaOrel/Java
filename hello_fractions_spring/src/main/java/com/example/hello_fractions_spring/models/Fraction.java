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

    public void subtract(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);

        this.numerator = newNumerator;
        this.denominator = commonDenominator;

        reduce(); // Сокращаем полученную дробь
    }

    public void multiply(Fraction other) {
        this.numerator *= other.numerator;
        this.denominator *= other.denominator;

        reduce(); // Сокращаем полученную дробь
    }

    public void divide(Fraction other) {
        if (other.numerator != 0) { // Проверка деления на ноль
            this.numerator *= other.denominator;
            this.denominator *= other.numerator;

            reduce(); // Сокращаем полученную дробь
        } else {
            // Обработка деления на ноль
            // Можно выбросить исключение или вернуть специальное значение
        }
    }
}
