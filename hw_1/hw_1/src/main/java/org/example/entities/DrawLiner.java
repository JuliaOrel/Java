package org.example.entities;

public class DrawLiner {

    public static void drawLine(int length, String direction, char symbol) {
        if (direction.equalsIgnoreCase("horizontal")) {
            drawHorizontalLine(length, symbol);
        } else if (direction.equalsIgnoreCase("vertical")) {
            drawVerticalLine(length, symbol);
        } else {
            System.out.println("Invalid direction. Please use 'horizontal' or 'vertical'.");
        }
    }

    private static void drawHorizontalLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println(); // Move to the next line after drawing the horizontal line
    }

    private static void drawVerticalLine(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.println(symbol);
        }
    }
}
