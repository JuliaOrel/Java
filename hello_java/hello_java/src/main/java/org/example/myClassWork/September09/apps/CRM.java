package org.example.myClassWork.September09.apps;

import com.rabbitmq.client.DeliverCallback;
import org.example.myClassWork.September09.MyRabbitMQ09;
import org.example.myClassWork.September09.models.Customer;
import org.example.myClassWork.September09.models.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class CRM {
    private ArrayList<Customer> customers=new ArrayList<>();
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    private MyRabbitMQ09 rabbitMQSiteUserRegister;
    private MyRabbitMQ09 rabbitMQCRMUserUpdate;
    private MyRabbitMQ09 rabbitMQCRMCustomerCreate;
    private MyRabbitMQ09 rabbitMQSiteCustomerUpdate;
    private Thread t;
    private Thread t2;
    DeliverCallback listenerUserRegister= (consumerTag, delivery) -> {
        // Таким образом я получаю тут пользователя
        User u=User.fromBytes(delivery.getBody());
        Customer c=Customer.fromUser(u);

        customers.add(c);
        rabbitMQCRMUserUpdate.publish(c);
    };

    DeliverCallback listenerSiteCustomerUpdate=(consumeTag, delivery)-> {
        User u = User.fromBytes(delivery.getBody());
        Optional<Customer> customer = customers.stream()
                .filter(c ->  c.getCustomer_id().equals(u.getCustomer_id()) )
                .findFirst();
        if (customer.isEmpty()) {
            System.out.println(" Ошибка синхронизации");
        } else {
            customer.get().updateFromUser(u);
        }
    };
    public CRM(){
        // Я мониторю события, связанные с созданием нового пользователя на сайте
        // Это Consumer
        rabbitMQSiteUserRegister=new MyRabbitMQ09("site.user.register");
        rabbitMQSiteUserRegister.useConsume(this.listenerUserRegister);
        t=new Thread(rabbitMQSiteUserRegister);
        t.start();
        //new Thread(rabbitMQSiteUserRegister).start();

        // Я сообщаю сайту, что пользователь обновился
        // Это Producer
        rabbitMQCRMUserUpdate=new MyRabbitMQ09("crm.user.update");

        //Я мониторю события, связанные с обновлением customer на сайте (Consumer)
        rabbitMQSiteCustomerUpdate=new MyRabbitMQ09("site.customer.update");
        rabbitMQSiteCustomerUpdate.useConsume(this.listenerSiteCustomerUpdate);
        t2=new Thread(rabbitMQSiteCustomerUpdate);
        t2.start();
        //new Thread(rabbitMQSiteCustomerUpdate).start();

        //Я ссобщаю сайту, что создан новый consumer - Producer
        rabbitMQCRMCustomerCreate=new MyRabbitMQ09("crm.customer.create");
    }


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
        rabbitMQCRMUserUpdate.disconnect();
        rabbitMQSiteUserRegister.disconnect();
        rabbitMQCRMCustomerCreate.disconnect();
        rabbitMQSiteCustomerUpdate.disconnect();
        t.interrupt();
        t2.interrupt();
    }
    public void commandAddCustomer(){
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setCustomer_id(UUID.randomUUID());
        newCustomer.setUser_id(null);

        customers.add(newCustomer); //Событие регистрации наступило

        rabbitMQCRMCustomerCreate.publish(newCustomer);

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
