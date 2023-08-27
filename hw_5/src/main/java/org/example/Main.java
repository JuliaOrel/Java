package org.example;

import org.example.threads.ThreadFirst;

public class Main {
    public static void main(String[] args) {
        ThreadFirst t1=new ThreadFirst();
        t1.run();
    }
}