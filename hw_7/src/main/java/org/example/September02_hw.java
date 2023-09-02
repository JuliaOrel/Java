package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class September02_hw implements Runnable{
    private Connection connection;
    @Override
    public void run() {
        ConnectToDb();
        try {
            displayMenu();
        } catch (SQLException e) {
            System.out.println("Something wrong"+e.getMessage());
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
    private void disconnectFromDb() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Not connected to the database.");
        }
    }
    private void displayMenu() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Make your choice:");
            System.out.println("1. Connect to DB");
            System.out.println("2. Disconnect from DB");
            System.out.println("3. Display all countries");
            System.out.println("4. Display all cities of country");
            System.out.println("5. Display all manufacturers and amount of cars");
            System.out.println("6. Functions");
            System.out.println("7. Filters");
            System.out.println("8. Settings");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ConnectToDb();
                    break;
                case 2:
                    disconnectFromDb();
                    break;
                case 3:
                    //displayAllCars();
                    break;
                case 4:
                    //displayManufacturers();
                    break;
                case 5:
                    //displayManufacturersAndCounts();
                    break;
                case 6:
                    //displayMenuFunct();
                    break;
                case 7:
                    //displayMenuFilters();
                    break;
                case 8:
                    //displayMenuSettings();
                    break;
                case 9:
                    return; // Выход из метода
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
            }
        }
    }

}
