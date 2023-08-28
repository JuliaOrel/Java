package org.example;

import org.example.threads.ThreadFirst;
import org.example.threads.ThreadFourth;
import org.example.threads.ThreadSecond;
import org.example.threads.ThreadThird;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
//        ThreadFirst t1=new ThreadFirst();
//        t1.run();

//        ThreadSecond t2=new ThreadSecond();
//        t2.run();

//        ThreadThird t3=new ThreadThird();
//        t3.run();

        ThreadFourth t4=new ThreadFourth();
        t4.run();
    }
}