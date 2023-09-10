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

public class Site {
    private ArrayList<User>users=new ArrayList<User>();
    public ArrayList<User> getUsers(){
        return users;
    }

    private MyRabbitMQ09 rabbitMQSiteUserRegister;
    private MyRabbitMQ09 rabbitMQCRMUserUpdate;
    private MyRabbitMQ09 rabbitMQCRMCustomerCreate;
    private MyRabbitMQ09 rabbitMQSiteCustomerUpdate;
    private Thread t;
    private Thread t2;
    public Site(){
        //Я сообщаю CRM, что появился новый пользователь на сайте - Producer
        rabbitMQSiteUserRegister = new MyRabbitMQ09("site.user.register");

        //Я мониторю обновления пользователя на CRM - Consumer
        rabbitMQCRMUserUpdate = new MyRabbitMQ09("crm.user.update");
        rabbitMQCRMUserUpdate.useConsume(listenerCrmUserUpdate);
        t=new Thread(rabbitMQCRMUserUpdate);
        t.start();
        //new Thread(rabbitMQCRMUserUpdate).start();

        // Я мониторю события, связанные с созданием нового customer на CRM
        // Это Consumer
        rabbitMQCRMCustomerCreate=new MyRabbitMQ09("crm.customer.create");
        rabbitMQCRMCustomerCreate.useConsume(this.listenerCRMCustomerCreate);
        t2=new Thread(rabbitMQCRMCustomerCreate);
        t2.start();

        // Я сообщаю CRM, что customer обновился
        // Это Producer
        rabbitMQSiteCustomerUpdate=new MyRabbitMQ09("site.customer.update");

    }

    DeliverCallback listenerCRMCustomerCreate=(consumerTag, delivery)->{
        // Таким образом я получаю тут customer
        Customer c=Customer.fromBytes(delivery.getBody());
        User u=User.fromCustomer(c);

        users.add(u);
        rabbitMQSiteCustomerUpdate.publish(u);
    };

    DeliverCallback listenerCrmUserUpdate  = (consumerTag, delivery) -> {
        Customer c = Customer.fromBytes(delivery.getBody());
        Optional<User> user = users.stream()
                .filter(u ->  u.getUser_id().equals(c.getUser_id()) )
                .findFirst();
        if (user.isEmpty()) {
            System.out.println(" Ошибка синхронизации");
        } else {
            user.get().updateFromCustomer(c);
        }


    };
    Scanner scanner=new Scanner(System.in);

    public void run() throws IOException {
        int userChoice;
        do {
            userChoice = menu();
            switch(userChoice){
                case 1: commandUserRegister();
                break;
                case 9: commandShowAll();
                break;

            }

        } while (userChoice != 0 );
        rabbitMQSiteUserRegister.disconnect();
        rabbitMQCRMUserUpdate.disconnect();
        rabbitMQCRMCustomerCreate.disconnect();
        rabbitMQSiteCustomerUpdate.disconnect();
        t.interrupt();
        t2.interrupt();


    }

    public void commandUserRegister(){
        System.out.println("Enter name: ");
        String name=scanner.nextLine();
        User newUser=new User();
        newUser.setName(name);
        newUser.setUser_id(UUID.randomUUID());
        newUser.setCustomer_id(null);
        users.add(newUser); //Событие регистрации наступило

        rabbitMQSiteUserRegister.publish(newUser);

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
