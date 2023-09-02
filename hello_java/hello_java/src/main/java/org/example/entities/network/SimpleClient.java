package org.example.entities.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class SimpleClient {
    public static void main(String[]args) throws IOException {
        try{
            Socket connect=new Socket("localhost", 33123);
            OutputStream out=connect.getOutputStream();

            DateFormat df=new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
            Date d=new Date();
            out.write(df.format(d).getBytes());
            connect.close();
        }catch(IOException e){
            throw  new RuntimeException(e);
        }

    }
}
