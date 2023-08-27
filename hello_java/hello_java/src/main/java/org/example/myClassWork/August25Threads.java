package org.example.myClassWork;

import org.example.threads.MyFirstThread;

public class August25Threads implements Runnable {
    @Override
    public void run() {
        System.out.println("Threads work");
        //mySelf();
        firstInit();
    }

    public void firstInit(){
        MyFirstThread t=new MyFirstThread("HelloThread");
        t.start();
    }

    public void mySelf(){
        Thread t=Thread.currentThread(); //get main thread
        System.out.println(t);//main
        System.out.println("Thread name is: "+ t.getName());
    }
}
