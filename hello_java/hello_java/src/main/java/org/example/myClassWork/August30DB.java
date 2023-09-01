package org.example.myClassWork;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class August30DB implements Runnable{
    private Connection connection;
    @Override
    public void run() {
        ConnectToDb();
        //ConnectToDbByProps();
        migrate();
        seed();
    }

    private void seed(){
        String sqlCreateAdmin = "INSERT INTO `users` " +
                "(`id`, `email`, `password`, `created_at`) " +
                "VALUES " +
                "(NULL, 'admin@admin.com', MD5('QweAsdZxc!23'), CURRENT_TIMESTAMP)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCreateAdmin);
        }
        // Анализируем повторение сущности ( за счет уникальности поля email)
        catch (SQLIntegrityConstraintViolationException e) {
            // Если такая сущность уже в базе есть - значит просто продолжаем работу
            System.out.println(" Admin уже создан в базе");
        }
        catch (SQLException e) {
            // Если что то другое пошло не так - то выходим из приложения по RuntimeException
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void migrate(){
        String sqlCrateTable = "" +
                "CREATE TABLE IF NOT EXISTS `pv121`.`users` " +
                "(" +
                "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT , " +
                "`email` VARCHAR(64) NOT NULL , " +
                "`password` VARCHAR(255) NOT NULL , " +
                "`created_at` DATETIME on update CURRENT_TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP , " +
                "PRIMARY KEY (`id`), UNIQUE (`email`)) ENGINE = InnoDB;";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCrateTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void ConnectToDbByProps() {
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String jdbcUrl = "jdbc:mysql://localhost:30121/" + props.getProperty("MYSQL_DATABASE");
        String MYSQL_USER = props.getProperty("MYSQL_USER");
        String MYSQL_PASSWORD = props.getProperty("MYSQL_PASSWORD");

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Connected to the database!");
            // connection.close(); // Не забудьте закрыть соединение, когда оно больше не нужно

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void ConnectToDb(){
        Dotenv dotenv = Dotenv.load();
        String jdbcUrl = "jdbc:mysql://localhost:30121/" + dotenv.get("MYSQL_DATABASE");
        String MYSQL_USER = dotenv.get("MYSQL_USER");
        String MYSQL_PASSWORD = dotenv.get("MYSQL_PASSWORD");

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Connected to the database!");
            // connection.close(); // Не забудьте закрыть соединение, когда оно больше не нужно

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
