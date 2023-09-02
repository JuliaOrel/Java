package org.example.entities.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[]args){
        try{
            ServerSocket serverSocket=new ServerSocket(33123);
            while(true){
                Socket client=null;
                while(client==null){
                    System.out.println("Start waiting for client");
                    client=serverSocket.accept();
                    System.out.println("Connect from "+ client.getLocalAddress());

                    InputStream in=client.getInputStream();
                    byte[] buffer=new byte[32];
                    buffer=in.readAllBytes();
                    System.out.println("read "+buffer);

                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
