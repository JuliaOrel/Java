package org.example.entities.chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[]args){
        try{
            ServerSocket serverSocket=new ServerSocket(33123);
            while(true){
                Socket client=null;
                while(client==null){
                    System.out.println("Start waiting for client");
                    client=serverSocket.accept();
                    System.out.println("Connect from "+ client.getLocalAddress());

                   ChatServerSocket newClient=new ChatServerSocket(client);
                   Thread t=new Thread(newClient);
                   t.start();

                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
