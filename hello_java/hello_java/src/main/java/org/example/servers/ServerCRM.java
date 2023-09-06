package org.example.servers;

import org.example.apps.CRM;

import java.io.IOException;
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

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
