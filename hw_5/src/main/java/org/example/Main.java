package org.example;

import org.example.threads.ThreadFirst;
import org.example.threads.ThreadSecond;

public class Main {
    public static void main(String[] args) {
//        ThreadFirst t1=new ThreadFirst();
//        t1.run();

        ThreadSecond t2=new ThreadSecond();
        t2.run();
    }
}