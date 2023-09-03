package org.example.entities.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatClientSocket implements Runnable{
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ChatClientSocket(Socket socket) {
        this.socket = socket;
        try {
            // Получу потоки
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                try {
                    String msg = (String) inputStream.readUTF();
                    System.out.println("Server Say: " + msg);
                } catch (Exception e) {
                    System.out.println("in ChatClientSocket.run: " +  e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
