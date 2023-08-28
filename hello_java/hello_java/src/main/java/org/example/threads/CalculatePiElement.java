package org.example.threads;

import lombok.Getter;

import java.util.Random;

public class CalculatePiElement implements Runnable{
    static Random rnd=new Random();
    private final int from;
    private final int to;
    @Getter
    public double result=0;

    public CalculatePiElement(int from, int to){
        this.from=from;
        this.to=to;
    }
    @Override
    public void run() {
        System.out.println("Start PI "+ this.hashCode() + " from: " + from +" to "+ to);
        try{
            for (int i = 0; i < rnd.nextInt(5); i++) {
                Thread.sleep(rnd.nextInt(100)+100);
            }
            Thread.sleep(rnd.nextInt(100,200));
        }catch(InterruptedException e){
            System.out.println("Can't pause this thread" + e.getMessage());
        }
        System.out.println("End PI "+ this.hashCode() + " from:" +from+ " to"+to);
    }
}
