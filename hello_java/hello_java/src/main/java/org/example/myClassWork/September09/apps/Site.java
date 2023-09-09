package org.example.myClassWork.September09.apps;

import org.example.myClassWork.September09.MyRabbitMQ09;
import org.example.myClassWork.September09.models.Customer;
import org.example.myClassWork.September09.models.User;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Site {
    private ArrayList<User>users=new ArrayList<User>();
    public ArrayList<User> getUsers(){
        return users;
    }

    private MyRabbitMQ09 rabbitMQSiteUserRegister;
    private MyRabbitMQ09 rabbitMQCreateCustomer;
    public Site(){}
    Scanner scanner=new Scanner(System.in);

    public void run(){
        int userChoice;
        do {
            userChoice = menu();
            switch(userChoice){
                case 1: commandUserRegister();
                break;
                case 9: commandShowAll();
                break;
            }

        } while (userChoice != 0);
    }

    public User createUserFromCustomer(Customer customer){
        User u=new User();
        u.setCustomer_id(customer.getCustomer_id());
        u.setName(customer.getName());
        u.setUser_id(UUID.randomUUID());
        users.add(u);
        return u;
    }

    public void commandUserRegister(){
        System.out.println("Enter name: ");
        String name=scanner.nextLine();
        User newUser=new User();
        newUser.setName(name);
        newUser.setUser_id(UUID.randomUUID());
        newUser.setCustomer_id(null);
        users.add(newUser); //Событие регистрации наступило

        //rabbitMQSiteUserRegister

        //Это если без rabbitMQ
        //Request r=new Request(RequestCommands.userRegister, newUser);
       // sendToCRM(r);
    }



    private void commandShowAll(){
        System.out.print("\n+--------------------------------+\n");
        for(User u: users){
            System.out.println(u);
        }
    }

    public int menu() {
        System.out.println("Выберете операцию");
        System.out.println("1 Создать пользователя (User)");
        System.out.println("9 Показать всех");
        System.out.println("0 Выход");


        int userChoice = -1;
        boolean isValidInput = false;

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
