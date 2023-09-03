package org.example.entities.chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[]args){
        try {
            // Начинаю слушать порт 33123
            ServerSocket serverSocket = new ServerSocket(33123);

            while (true) {
                // Socket clientSocket = null;
                while (true) {
                    System.out.println(" Start wait client");
                    // Создаю новый поток для обработки этого клиента
                    ChatServerSocket newClient = new ChatServerSocket(serverSocket.accept());
                    Thread t = new Thread(newClient);
                    t.start();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
