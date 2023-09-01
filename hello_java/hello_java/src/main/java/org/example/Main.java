package org.example;

import org.example.entities.User;
import org.example.myClassWork.*;

/*
F:\Java_jdk\jdk-17\bin\java.exe
"-javaagent:F:\JetBrains\IntelliJ IDEA 2023.2\lib\idea_rt.jar=59039:F:\JetBrains\IntelliJ IDEA 2023.2\bin"
-Dfile.encoding=UTF-8
-classpath C:\Users\User\IdeaProjects\Java\hello_java\hello_java\target\classes org.example.Main

*/
public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        User u=new User();
//        u.setEmail("yy0709@ukr.net");
//        u.setPassword("j23j145");
        //August18Academy work=new August18Academy();
        //August18Button button=new August18Button();
        //button.run();


//        August18Collection collection=new August18Collection();
//        collection.run();

//        August19Stream work =new August19Stream();
//        work.run();

//        August19Posts work=new August19Posts();
//        work.run();

        //August25Threads work=new August25Threads();
        //work.run();

//        August26Threads work=new August26Threads();
//        work.run();
//
//        Thread t=Thread.currentThread();
//        System.out.println("Finish app in thread "+ t.getName());

//        August29Files work=new August29Files();
//        work.run();

//        August30DB work=new August30DB();
//        work.run();

        September_01_redis work=new September_01_redis();
        work.run();
    }
}