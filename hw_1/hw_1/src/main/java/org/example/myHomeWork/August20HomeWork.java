package org.example.myHomeWork;

import java.util.Scanner;
public class August20HomeWork implements Runnable
{

    @Override
    public void run() {
        textOut();
        //calculatePercents();
        //combineDigits();
        //swapDigits();
        //seasonChecker();
        lengthConverter();
    }

    private void textOut(){
        System.out.println("Task 1 \n");
        System.out.println("“Your time is limited,");
        System.out.println("\tso don’t waste it");
        System.out.println("\t\tliving someone else’s life”");
        System.out.println("\t\t\tSteve Jobs");
    }

    private void calculatePercents(){
        System.out.println("Task 2 \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        double value = scanner.nextDouble();
        System.out.print("Введите процент: ");
        double percentage = scanner.nextDouble();
        double result = (percentage / 100) * value;
        System.out.println(percentage + "% от " + value + " = " + result);

    }

    private void combineDigits(){
        System.out.println("Task 3 \n");
        Scanner scanner = new Scanner(System.in);
        int firstDigit, secondDigit, thirdDigit;
        do{
            System.out.print("Введите первую цифру: ");
            firstDigit=scanner.nextInt();
            checkDigit(firstDigit);
        }while(firstDigit<1 || firstDigit>9);


        do{
            System.out.print("Введите вторую  цифру: ");
            secondDigit=scanner.nextInt();
            checkDigit(secondDigit);
        }while(secondDigit<1 || secondDigit>9);

        do{
            System.out.print("Введите третью   цифру: ");
            thirdDigit=scanner.nextInt();
            checkDigit(thirdDigit);
        }while(thirdDigit<1 || thirdDigit>9);


        int combinedNumber = firstDigit * 100 + secondDigit * 10 + thirdDigit;

        System.out.println("Result: " + combinedNumber);


    }

    private void checkDigit(int digit){
        if(digit<1 || digit>9){
            System.out.println("Digit must be from 1 to 9");
            return;
        }
    }

    private void swapDigits(){
        System.out.println("Task 4 \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите шестизначное число: ");
        int number = scanner.nextInt();
        if (number >= 100000 && number <= 999999) {
            int firstDigit = number / 100000;
            int secondDigit = (number / 10000) % 10;
            int thirdDigit = (number / 1000) % 10;
            int fourthDigit = (number / 100) % 10;
            int fifthDigit = (number / 10) % 10;
            int sixthDigit = number % 10;

            int newNumber = sixthDigit * 100000 + fifthDigit * 10000 +
                    thirdDigit * 1000 + fourthDigit * 100 +
                    secondDigit * 10 + firstDigit;

            System.out.println("Исходное число: " + number);
            System.out.println("Число после замены: " + newNumber);
        } else {
            System.out.println("Ошибка: Введите шестизначное число.");
        }
    }

    private void seasonChecker(){
        System.out.println("Task 5 \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер месяца (от 1 до 12): ");
        int month = scanner.nextInt();
        String season;
        switch (month){
            case 1:
            case 2:
            case 12:
                season="Winter";
                break;
            case 3:
            case 4:
            case 5:
                season ="Spring";
                break;
            case 6:
            case 7:
            case 8:
                season ="Summer";
                break;
            case 9:
            case 10:
            case 11:
                season ="Autumn";
                break;
            default:
                season="Enter correct number of month";
                break;
        }
        System.out.println("Season " + season);
    }

    private void lengthConverter(){
        System.out.println("Task 6 \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество метров: ");
        double meters = scanner.nextDouble();
        int choice;
        do{
            System.out.println("Выберите единицу измерения для перевода:");
            System.out.println("1. Сантиметры");
            System.out.println("2. Километры");
            System.out.println("3. Миллиметры");
            choice = scanner.nextInt();
        }while(choice<1 || choice >3);

        double result;

        switch(choice){
            case 1:
                result = meters * 100;
                System.out.println(meters + " метров = " + result + " сантиметров");
                break;
            case 2:
                result = meters * 1000;
                System.out.println(meters + " метров = " + result + " километров");
                break;
            case 3:
                result = meters * 1000;
                System.out.println(meters + " метров = " + result + "миллиметров");
                break;
            default:
                System.out.println("Некорректный выблор");

        }


    }


}
