package org.example.myClassWork.september_06.crm.servers;

import com.mysql.cj.PreparedQuery;
import org.example.myClassWork.september_06.crm.apps.Site;
import org.example.myClassWork.september_06.crm.models.Customer;
import org.example.myClassWork.september_06.crm.models.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSite implements Runnable{
    private Site site;
    public ServerSite(Site site){
        this.site=site;
    }
    @Override
    public void run() {
        System.out.println(" Start wait messages from CRM ");
        try {
            ServerSocket serverSocket =new ServerSocket(33124);
            while(true){
                Socket crmSocket=null;
                while ((crmSocket == null)) {
                    crmSocket=serverSocket.accept();
                    System.out.println("Connect Form: "+crmSocket.getLocalAddress());

                    ObjectInputStream inputStream=new ObjectInputStream(crmSocket.getInputStream());
                    try {
                        Request request=(Request)inputStream.readObject();
                        System.out.println(request);

                        switch(request.getCommand()){
                            case customerRegister:
                                User newUser=site.createUserFromCustomer((Customer) request.getBody());
                                ObjectOutputStream outputStream=new ObjectOutputStream(crmSocket.getOutputStream());

                                Response r=new Response();
                                r.setStatus(ResponseStatus.ok);
                                r.setBody(newUser);
                                outputStream.writeObject(r);
                                crmSocket.close();
                                break;
                        }

                    }catch(Exception ex){}

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
