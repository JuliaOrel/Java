package org.example.myHomeWork;

import org.example.entities.DrawLiner;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.example.entities.ArraySorter.sortAscending;
import static org.example.entities.ArraySorter.sortDescending;
import static org.example.entities.DrawLiner.drawLine;

public class August20HomeWork implements Runnable
{

    @Override
    public void run() {
        textOut();
        //calculatePercents();
        //combineDigits();
        //swapDigits();
        //seasonChecker();
        //lengthConverter();
        //oddNumberInRange();
        //multiplicationTable();
        //operationtsWithArray();
        //createNewArrays();
        //draw();
        sortArray();
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
    private void oddNumberInRange(){
        System.out.println("Task 7 \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начало диапазона: ");
        int start = scanner.nextInt();

        System.out.print("Введите конец диапазона: ");
        int end = scanner.nextInt();

        // Нормализация границ диапазона
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        System.out.println("Нечетные числа в диапазоне от " + start + " до " + end + ":");
        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }

    }

    private void multiplicationTable(){
        System.out.println("Task 8 \n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало диапазона: ");
        int start = scanner.nextInt();

        System.out.print("Введите конец диапазона: ");
        int end = scanner.nextInt();

        System.out.println("Таблица умножения в диапазоне от " + start + " до " + end + ":");

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
            System.out.println("...........................");
        }


    }

    private void operationtsWithArray(){
        System.out.println("Task 9 \n");

        int[] array = new int[10]; // размер массива
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101) - 50; // случайные числа от -50 до 50
        }

        // Инициализируем переменные для хранения результатов
        int min = array[0];
        int max = array[0];
        int negativeCount = 0;
        int positiveCount = 0;
        int zeroCount = 0;

        for (int num : array) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
            if (num < 0) {
                negativeCount++;
            } else if (num > 0) {
                positiveCount++;
            } else {
                zeroCount++;
            }
        }

        // Выводим результаты на экран
        System.out.println("Массив:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println("\nМинимальный элемент: " + min);
        System.out.println("Максимальный элемент: " + max);
        System.out.println("Количество отрицательных элементов: " + negativeCount);
        System.out.println("Количество положительных элементов: " + positiveCount);
        System.out.println("Количество нулей: " + zeroCount);

    }

    private void createNewArrays(){
        System.out.println("Task 10 \n");

        int[]array=new int[10];
        Random random=new Random();
        for(int i=0; i < array.length; i++){
            array[i]=random.nextInt(101)-50;
        }

        int[]evenArray=new int[array.length];
        int[]oddArray =new int[array.length];
        int[]negativeArray =new int[array.length];
        int[]positiveArray =new int[array.length];

        int evenCount = 0;
        int oddCount = 0;
        int negativeCount = 0;
        int positiveCount = 0;

        for(int num: array){
            if (num % 2 == 0) {

                evenArray[evenCount++]=num;
            }
            else{
                oddArray[oddCount++]=num;
            }
            if(num<0){
                negativeArray[negativeCount++]=num;
            }else if(num>0){
                positiveArray[positiveCount++]=num;
            }
        }

        // Создаем и заполняем новые массивы с учетом размеров фильтрованных массивов
        int[] finalEvenArray = new int[evenCount];
        System.arraycopy(evenArray, 0, finalEvenArray, 0, evenCount);

        int[] finalOddArray = new int[oddCount];
        System.arraycopy(oddArray, 0, finalOddArray, 0, oddCount);

        int[] finalNegativeArray = new int[negativeCount];
        System.arraycopy(negativeArray, 0, finalNegativeArray, 0, negativeCount);

        int[] finalPositiveArray = new int[positiveCount];
        System.arraycopy(positiveArray, 0, finalPositiveArray, 0, positiveCount);

        // Выводим результаты на экран
        System.out.println("Исходный массив:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println("\nЧетные числа:");
        for (int num : finalEvenArray) {
            System.out.print(num + " ");
        }
        System.out.println("\nНечетные числа:");
        for (int num : finalOddArray) {
            System.out.print(num + " ");
        }
        System.out.println("\nОтрицательные числа:");
        for (int num : finalNegativeArray) {
            System.out.print(num + " ");
        }
        System.out.println("\nПоложительные числа:");
        for (int num : finalPositiveArray) {
            System.out.print(num + " ");
        }

    }

    private void draw(){
        System.out.println("Task 11 \n");

        drawLine(10, "horizontal", '*');
        drawLine(5, "vertical", '#');
    }

    private void sortArray(){
        System.out.println("Task 12 \n");
        Scanner scanner = new Scanner(System.in);
        int[]array=new int [10];
        Random random=new Random();
        for(int i=0; i<array.length;i++){
            array[i]=random.nextInt(201);
        }
        System.out.print("Enter sorting order (asc/desc): ");
        String order = scanner.next();

        if (order.equalsIgnoreCase("asc")) {
            sortAscending(array);
            System.out.println("Sorted array in ascending order: " + Arrays.toString(array));
        } else if (order.equalsIgnoreCase("desc")) {
            sortDescending(array);
            System.out.println("Sorted array in descending order: " + Arrays.toString(array));
        } else {
            System.out.println("Invalid sorting order. Please use 'asc' or 'desc'.");
        }
    }


}
