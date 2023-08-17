package org.example;

import org.example.entities.User;

/*
F:\Java_jdk\jdk-17\bin\java.exe
"-javaagent:F:\JetBrains\IntelliJ IDEA 2023.2\lib\idea_rt.jar=59039:F:\JetBrains\IntelliJ IDEA 2023.2\bin"
-Dfile.encoding=UTF-8
-classpath C:\Users\User\IdeaProjects\Java\hello_java\hello_java\target\classes org.example.Main

*/
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        User u=new User();
        u.setEmail("yy0709@ukr.net");
        u.setPassword("j23j145");
    }
}