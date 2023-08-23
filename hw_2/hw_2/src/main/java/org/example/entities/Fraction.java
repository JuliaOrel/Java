package org.example.entities;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(){
        this.numerator=0;
        this.denominator=1;
    }

    public Fraction(int numerator, int denominator){
        if(denominator==0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator=numerator;
        this.denominator=denominator;
    }

    public void setFraction(int wholeNumber){
        this.numerator=wholeNumber;
        this.denominator=1;
    }

    public void setFraction(int numerator, int denominator){
        Fraction frc=new Fraction(numerator, denominator);
        this.numerator=numerator;
        this.denominator=denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
