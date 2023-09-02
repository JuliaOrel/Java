package org.example.entities.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatServerSocket implements Runnable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    public ChatServerSocket(Socket socket){
        this.socket=socket;
        try{
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            inputStream=new ObjectInputStream((socket.getInputStream()));
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Connect from: "+socket.getLocalAddress()+ "\t Thread "+Thread.currentThread().getName() );
        do{

        }while(socket.isConnected());
        System.out.println("Disconnected");
    }
}
