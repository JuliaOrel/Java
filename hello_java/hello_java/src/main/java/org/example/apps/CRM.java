package org.example.apps;

import org.example.models.Customer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CRM {
    private ArrayList<Customer> customers=new ArrayList<>();
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public CRM(){

    }
    public void run(){
        int userChoice;
        do{
            userChoice=menu();
            switch (userChoice){
                case 1: commandAddCustomer();
                    break;
                case 9: commandShowAll();
                    break;
            }
        }while(userChoice!= 0);
    }
    private void commandAddCustomer(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setCustomer_id(UUID.randomUUID());
        newCustomer.setUser_id(null);

        customers.add(newCustomer);
    }

    private void commandShowAll() {
        System.out.println("\n+------------------------------+\n");
        for (Customer c: customers) {
            System.out.println(c);
        }
        System.out.println("\n+------------------------------+\n");
    }
    Scanner scanner=new Scanner(System.in);

    public int menu(){
        System.out.println("Choose operation: ");
        System.out.println("1.Create customer: ");
        System.out.println("9.Show all: ");
        System.out.println("0.Exit: ");
        int userChoice=-1;
        boolean isValidInput=false;
        while (!isValidInput) {
            System.out.print("Введите цифру: ");
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                isValidInput = true;
            } else {
                System.out.println("Вы ввели неверное значение. Пожалуйста, введите целое число.");
                scanner.nextLine(); // Очищаем буфер ввода
            }
        }
        scanner.nextLine();
        return  userChoice;
    }
}
