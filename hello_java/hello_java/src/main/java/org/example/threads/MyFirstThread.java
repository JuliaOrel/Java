package org.example.threads;

public class MyFirstThread extends Thread{

    private final String name;
    public MyFirstThread(String name){
        this.name=name;
        //тут я еще в главном потоке, поэтому я переименовываю главный поток
        Thread.currentThread().setName(name);
    }
    @Override
    public void run() {
        System.out.println("Thread "+ Thread.currentThread().getName()+ " start working");
        //тут я уже в своем потоке
        Thread.currentThread().setName("fofo");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Can't pause this thread: "+ e.getMessage());
        }

        System.out.println("Thread "+ Thread.currentThread().getName()+ " stop working");
    }
}
