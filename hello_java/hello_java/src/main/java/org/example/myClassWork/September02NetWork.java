package org.example.myClassWork;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class September02NetWork implements Runnable{
    @Override
    public void run() {
        try {
            getByName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    private void getByName() throws UnknownHostException {
        try{
            InetAddress address= InetAddress.getByName("mysql");
            System.out.println(address.getHostName());
            System.out.println(address.getAddress());
            System.out.println(address.getCanonicalHostName());
        }catch (UnknownHostException e){
            System.out.println(e.getMessage());
        }

    }
}
