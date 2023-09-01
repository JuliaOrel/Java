package org.example.September_01_hw_db;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.Scanner;

public class September_hw_6_db implements Runnable{
    private Connection connection;
    @Override
    public void run() {
        //ConnectToDb();
        try {
            displayMenu();
        } catch (SQLException e) {
            System.out.println("Something wrong"+e.getMessage());
        }
        //migrate();
        //insertCar("Toyota", "Camry", 2.5, 2022, "Silver", "Sedan");
        //("BMW", "X5", 3.0, 2023, "Black", "SUV");
        //insertCar("Audi", "A4", 2.0, 2023, "Silver", "Sedan");
        //insertCar("Mercedes-Benz", "E-Class", 3.0, 2023, "Blue", "Sedan");
    }

    private void displayMenu() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Make your choice:");
            System.out.println("1. Connect to DB");
            System.out.println("2. Disconnect from DB");
            System.out.println("3. Display all cars");
            System.out.println("4. Display all manufacturers");
            System.out.println("5. Display all manufacturers and amount of cars");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ConnectToDb();
                    break;
                case 2:
                    disconnectFromDb();
                    break;
                case 3:
                    displayAllCars();
                    break;
                case 4:
                    displayManufacturers();
                    break;
                case 5:
                    displayManufacturersAndCounts();
                    break;
                case 6:
                    return; // Выход из метода
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
            }
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

    private void migrate(){
        String sqlCrateTable = "" +
                "CREATE TABLE IF NOT EXISTS cars " +
                "(" +
                "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT , " +
                "`manufacturer` TEXT NOT NULL,"+
                "`model` TEXT NOT NULL," +
                "`engineVolume` REAL NOT NULL," +
                "`manufactureYear` INTEGER NOT NULL," +
                "`color` TEXT NOT NULL," +
                "`carType` TEXT NOT NULL," +
                "`created_at` DATETIME on update CURRENT_TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP , " +
                "PRIMARY KEY (`id`)" +
                ") ENGINE = InnoDB;";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCrateTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void displayAllCars() {
        if(connection!=null){
            String sql = "SELECT * FROM cars";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String manufacturer = resultSet.getString("manufacturer");
                    String model = resultSet.getString("model");
                    double engineVolume = resultSet.getDouble("engineVolume");
                    int manufactureYear = resultSet.getInt("manufactureYear");
                    String color = resultSet.getString("color");
                    String carType = resultSet.getString("carType");
                    System.out.println("ID: " + id + ", Manufacturer: " + manufacturer + ", Model: " + model + ", Engine Volume: " + engineVolume + ", Manufacture Year: " + manufactureYear + ", Color: " + color + ", Car Type: " + carType);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Coonect to DB");
        }

    }

    private void insertCar(String manufacturer, String model, double engineVolume, int manufactureYear, String color, String carType) {
        String sql = "INSERT INTO cars (manufacturer, model, engineVolume, manufactureYear, color, carType) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, manufacturer);
            preparedStatement.setString(2, model);
            preparedStatement.setDouble(3, engineVolume);
            preparedStatement.setInt(4, manufactureYear);
            preparedStatement.setString(5, color);
            preparedStatement.setString(6, carType);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Автомобиль добавлен успешно.");
            } else {
                System.out.println("Не удалось добавить автомобиль.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayManufacturers() {
        if(connection!=null){
            String sql = "SELECT DISTINCT manufacturer FROM cars";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String manufacturer = resultSet.getString("manufacturer");
                    System.out.println("Manufacturer: " + manufacturer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Coonect to DB");
        }
    }
    private void displayManufacturersAndCounts() {
        if(connection!=null){
            String sql = "SELECT manufacturer, COUNT(*) AS carCount FROM cars GROUP BY manufacturer";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String manufacturer = resultSet.getString("manufacturer");
                    int carCount = resultSet.getInt("carCount");
                    System.out.println("Manufacturer: " + manufacturer + ", Car Count: " + carCount);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Coonect to DB");
        }

    }
}
