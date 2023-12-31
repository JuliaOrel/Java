package org.example.September_01_hw_db;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.StandardSocketOptions;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class September_hw_6_db implements Runnable{
    private Connection connection;
    @Override
    public void run() {
        ConnectToDb();
        try {
            displayMenu();
        } catch (SQLException e) {
            System.out.println("Something wrong"+e.getMessage());
        }
        //migrate();
        //insertCar("Toyota", "Corolla", 2.5, 2022, "Silver", "Sedan");
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
                    displayAllCars();
                    break;
                case 4:
                    displayManufacturers();
                    break;
                case 5:
                    displayManufacturersAndCounts();
                    break;
                case 6:
                    displayMenuFunct();
                    break;
                case 7:
                    displayMenuFilters();
                    break;
                case 8:
                    displayMenuSettings();
                    break;
                case 9:
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

    private void displayMenuFunct() throws SQLException{
        if(connection!=null){
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Make your choice:");
                System.out.println("1. Display manufacturer with most cars");
                System.out.println("2. Display manufacturer with least cars");
                System.out.println("3. Display cars by year");
                System.out.println("4. Display cars with the year in range");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayManufacturerWithMostCars();
                        break;
                    case 2:
                        displayManufacturerWithLeastCars();
                        break;
                    case 3:
                        displayCarsByYear();
                        break;
                    case 4:
                        displayCarsByRange();
                        break;
                    case 5:
                        return; // Выход из метода
                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
                }
            }
        }
        else{
            System.out.println("Connect to DB");
        }

    }

    private void displayManufacturerWithMostCars() {
        String sqlQuery = "SELECT manufacturer, COUNT(*) AS carCount FROM cars GROUP BY manufacturer HAVING COUNT(*) = (SELECT MAX(carCount) FROM (SELECT COUNT(*) AS carCount FROM cars GROUP BY manufacturer) AS carCounts)";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                int carCount = resultSet.getInt("carCount");
                System.out.println("Производитель с наибольшим количеством автомобилей: " + manufacturer + " (" + carCount + " автомобилей)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayManufacturerWithLeastCars() {
        String sqlQuery = "SELECT manufacturer, COUNT(*) AS carCount FROM cars GROUP BY manufacturer HAVING COUNT(*) = (SELECT MIN(carCount) FROM (SELECT COUNT(*) AS carCount FROM cars GROUP BY manufacturer) AS carCounts)";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                int carCount = resultSet.getInt("carCount");
                System.out.println("Производитель с наименьшим количеством автомобилей: " + manufacturer + " (" + carCount + " автомобилей)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCarsByYear() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input year");
        int year = scanner.nextInt();
        String sqlQuery = "SELECT * FROM cars WHERE manufactureYear = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                // Другие поля...
                System.out.println("Производитель: " + manufacturer + ", Модель: " + model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCarsByRange(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input start year");
        int startYear=scanner.nextInt();
        System.out.println("Input end year");
        int endYear=scanner.nextInt();
        String sqlQuery = "SELECT * FROM cars WHERE manufactureYear BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, startYear);
            preparedStatement.setInt(2, endYear);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                // Другие поля...
                System.out.println("Производитель: " + manufacturer + ", Модель: " + model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayMenuFilters() throws SQLException{
        if(connection!=null){
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Make your choice:");
                System.out.println("1. Display all cars by manufacturer");
                System.out.println("2. Filter by color");
                System.out.println("3. Filter by engine");
                System.out.println("4. Filter by car type");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayCarsByManufacturer();
                        break;
                    case 2:
                        displayCarsByColor();
                        break;
                    case 3:
                        displayCarsByEngineVolume();
                        break;
                    case 4:
                        displayCarsByType();
                        break;
                    case 5:
                        return; // Выход из метода
                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
                }
            }
        }
        else{
            System.out.println("Connect to DB");
        }

    }

    private void displayCarsByManufacturer() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input manufacturer: ");
        String manufacturer=scanner.next();
        String sqlQuery = "SELECT * FROM cars WHERE manufacturer = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, manufacturer);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                double engineVolume = resultSet.getDouble("engineVolume");
                int manufactureYear = resultSet.getInt("manufactureYear");
                String color = resultSet.getString("color");
                String carType = resultSet.getString("carType");

                System.out.println("ID: " + id);
                System.out.println("Модель: " + model);
                System.out.println("Объем двигателя: " + engineVolume);
                System.out.println("Год выпуска: " + manufactureYear);
                System.out.println("Цвет: " + color);
                System.out.println("Тип автомобиля: " + carType);
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCarsByColor() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input color: ");
        String color=scanner.next();
        String sqlQuery = "SELECT * FROM cars WHERE color = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, color);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                double engineVolume = resultSet.getDouble("engineVolume");
                int manufactureYear = resultSet.getInt("manufactureYear");
                String carType = resultSet.getString("carType");

                System.out.println("ID: " + id);
                System.out.println("Производитель: " + manufacturer);
                System.out.println("Модель: " + model);
                System.out.println("Объем двигателя: " + engineVolume);
                System.out.println("Год выпуска: " + manufactureYear);
                System.out.println("Тип автомобиля: " + carType);
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }
    private void displayCarsByEngineVolume() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input min volume");
        double minVolume=scanner.nextDouble();
        System.out.println("Input max volume");
        double maxVolume=scanner.nextDouble();
        String sqlQuery = "SELECT * FROM cars WHERE engineVolume BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setDouble(1, minVolume);
            preparedStatement.setDouble(2, maxVolume);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int manufactureYear = resultSet.getInt("manufactureYear");
                String color = resultSet.getString("color");
                String carType = resultSet.getString("carType");

                System.out.println("ID: " + id);
                System.out.println("Производитель: " + manufacturer);
                System.out.println("Модель: " + model);
                System.out.println("Год выпуска: " + manufactureYear);
                System.out.println("Цвет: " + color);
                System.out.println("Тип автомобиля: " + carType);
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCarsByType() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input car's type: ");
        String carType=scanner.next();
        String sqlQuery = "SELECT * FROM cars WHERE carType = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, carType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int manufactureYear = resultSet.getInt("manufactureYear");
                double engineVolume = resultSet.getDouble("engineVolume");
                String color = resultSet.getString("color");


                System.out.println("ID: " + id);
                System.out.println("Производитель: " + manufacturer);
                System.out.println("Модель: " + model);
                System.out.println("Год выпуска: " + manufactureYear);
                System.out.println("Цвет: " + color);
                System.out.println("Объем двигателя: " + engineVolume);
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void displayMenuSettings() throws SQLException{
        if(connection!=null){
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Make your choice:");
                System.out.println("1. Add row");
                System.out.println("2. Delete row");
                System.out.println("3. Update row");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addCar();
                        break;
                    case 2:
                        deleteCar();
                        break;
                    case 3:
                        func();
                        break;
                    case 4:
                        return; // Выход из метода
                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
                }
            }
        }
        else{
            System.out.println("Connect to DB");
        }

    }
    private void addCar() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input manufacturer: ");
        String manufacturer=scanner.next();
        System.out.println("Input model: ");
        String model=scanner.next();
        try{
            System.out.println("Input engineVolume: ");
            double engineVolume=scanner.nextDouble();
        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Input engineVolume: ");
        double engineVolume=scanner.nextDouble();
        System.out.println("Input manufactureYear: ");
        int manufactureYear=scanner.nextInt();
        System.out.println("Input color: ");
        String color=scanner.next();
        System.out.println("Input car type: ");
        String carType=scanner.next();
        String sqlQuery = "INSERT INTO cars (manufacturer, model, engineVolume, manufactureYear, color, carType) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, manufacturer);
            preparedStatement.setString(2, model);
            preparedStatement.setDouble(3, engineVolume);
            preparedStatement.setInt(4, manufactureYear);
            preparedStatement.setString(5, color);
            preparedStatement.setString(6, carType);

            preparedStatement.executeUpdate();
            System.out.println("Запись добавлена в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCar(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input id");
        int carId=scanner.nextInt();

        String sqlQuery = "DELETE FROM cars WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, carId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Запись удалена из базы данных.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void func() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input id");
        updateCar(scanner.nextInt());
    }

    private void updateCar(int carId) throws SQLException{

        if(connection!=null){
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("What note do you want to update?");
                System.out.println("1. Manufacturer");
                System.out.println("2. Model");
                System.out.println("3. EngineVolume");
                System.out.println("4. ManufactureYear");
                System.out.println("5. Color");
                System.out.println("6. Car type");
                System.out.println("7. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        updateManuf(carId);
                        break;
                    case 2:
                        updateModel(carId);
                        break;
                    case 3:
                        updateEngineVolume(carId);
                        break;
                    case 4:
                        updateManufactureYear(carId);
                        break;
                    case 5:
                        updateColor(carId);
                        break;
                    case 6:
                        updateCarType(carId);
                        break;
                    case 7:
                        return; // Выход из метода
                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
                }
            }
        }
        else{
            System.out.println("Connect to DB");
        }
    }

    private void updateManuf(int carId){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input manufacturer: ");
        String newManufacturer=scanner.next();
        String sqlQuery = "UPDATE cars SET manufacturer = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, newManufacturer);
            preparedStatement.setInt(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateModel(int carId){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input model: ");
        String newModel=scanner.next();
        String sqlQuery = "UPDATE cars SET model = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, newModel);
            preparedStatement.setInt(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateColor(int carId){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input color: ");
        String newColor=scanner.next();
        String sqlQuery = "UPDATE cars SET color = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, newColor);
            preparedStatement.setInt(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCarType(int carId){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input car type: ");
        String newCarType=scanner.next();
        String sqlQuery = "UPDATE cars SET carType = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, newCarType);
            preparedStatement.setInt(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateManufactureYear(int carId){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input manufactureYear: ");
        int newManufactureYear=scanner.nextInt();
        String sqlQuery = "UPDATE cars SET manufactureYear = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, newManufactureYear);
            preparedStatement.setInt(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEngineVolume(int carId){
        Scanner scanner=new Scanner(System.in);
        double newEngineVolume=0;
        try{
            System.out.println("Input engineVolume: ");
            newEngineVolume=scanner.nextDouble();
        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
        String sqlQuery = "UPDATE cars SET engineVolume = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setDouble(1, newEngineVolume);
            preparedStatement.setDouble(2, carId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Производитель обновлен.");
            } else {
                System.out.println("Запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
