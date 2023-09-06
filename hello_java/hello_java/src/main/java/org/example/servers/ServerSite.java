package org.example.servers;

import org.example.apps.CRM;
import org.example.apps.Site;

import java.io.IOException;
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

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
