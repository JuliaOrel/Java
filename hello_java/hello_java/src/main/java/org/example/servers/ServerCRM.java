package org.example.servers;

import org.example.apps.CRM;
import org.example.models.Customer;
import org.example.models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCRM implements Runnable{
    private CRM crm;
    public ServerCRM(CRM crm){
        this.crm=crm;
    }
    @Override
    public void run() {
        System.out.println(" Start wait messages from Site ");
        try {
            ServerSocket serverSocket =new ServerSocket(33123);
            while(true){
                Socket siteSocket=null;
                while ((siteSocket == null)) {
                    siteSocket=serverSocket.accept();
                    System.out.println("Connect Form: "+siteSocket.getLocalAddress());
                    ObjectInputStream inputStream=new ObjectInputStream(siteSocket.getInputStream());
                    try{
                       Request request=(Request) inputStream.readObject();
                       System.out.println(request);
                       switch(request.getCommand()){
                           case userRegister: Customer newCustomer=crm.createCUstomerFromUser((User) request.getBody());
                               ObjectOutputStream outputStream=new ObjectOutputStream(siteSocket.getOutputStream());
                               Response r=new Response();
                               r.setStatus(ResponseStatus.ok);
                               r.setBody(newCustomer);
                               outputStream.writeObject(r);
                               siteSocket.close();
                           break;
                       }
                    }catch (Exception e){}

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
