package org.example.myClassWork.September09.apps;

import com.rabbitmq.client.DeliverCallback;
import org.example.myClassWork.September09.MyRabbitMQ09;
import org.example.myClassWork.September09.models.Customer;
import org.example.myClassWork.September09.models.User;
import org.example.myClassWork.september_08.DTOObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CRM {
    private ArrayList<Customer> customers=new ArrayList<>();
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    private MyRabbitMQ09 rabbitMQSiteUserRegister;
    private MyRabbitMQ09 rabbitMQCreateCustomer;
    public CRM(){
        rabbitMQSiteUserRegister=new MyRabbitMQ09("site.user.register");
        rabbitMQSiteUserRegister.useConsume(this.listenerUserRegister);
        new Thread(rabbitMQSiteUserRegister).start();

        rabbitMQCreateCustomer=new MyRabbitMQ09("crm.customer.update");
    }
    DeliverCallback listenerUserRegister= (consumerTag, delivery) -> {
        User u=User.fromBytes(delivery.getBody());
        Customer c=Customer.fromUser(u);

        customers.add(c);
        rabbitMQCreateCustomer.publish(c);
    };

    public void run() throws IOException {
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
        rabbitMQCreateCustomer.disconnect();
        rabbitMQSiteUserRegister.disconnect();
    }
    public void commandAddCustomer(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setCustomer_id(UUID.randomUUID());
        newCustomer.setUser_id(null);

        customers.add(newCustomer); //Событие регистрации наступило

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
