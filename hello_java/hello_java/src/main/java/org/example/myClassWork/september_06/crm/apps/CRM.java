package org.example.myClassWork.september_06.crm.apps;

import org.example.myClassWork.september_06.crm.models.Customer;
import org.example.myClassWork.september_06.crm.models.User;
import org.example.myClassWork.september_06.crm.servers.Request;
import org.example.myClassWork.september_06.crm.servers.RequestCommands;
import org.example.myClassWork.september_06.crm.servers.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CRM {
    private ArrayList<Customer> customers=new ArrayList<>();
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public CRM(){}

    public Customer createCUstomerFromUser(User user){
        Customer c=new Customer();
        c.setUser_id(user.getUser_id());
        c.setName(user.getName());
        c.setCustomer_id(UUID.randomUUID());
        customers.add(c);
        return c;
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

        customers.add(newCustomer); //Событие регистрации наступило
        Request r=new Request(RequestCommands.customerRegister, newCustomer);
        sendToSite(r);
    }

    private void sendToSite(Request r){
        try{
            Socket connect=new Socket("localhost", 33124);
            ObjectOutputStream outputStream=new ObjectOutputStream(connect.getOutputStream());
            outputStream.writeObject(r);

            ObjectInputStream inputStream=new ObjectInputStream(connect.getInputStream());
            Response res=(Response)inputStream.readObject();
            User newUser=(User)res.getBody();
            System.out.println(newUser);

            ((Customer) r.getBody()).setUser_id(newUser.getUser_id());
            connect.close();
        }catch(Exception ex){

        }

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
