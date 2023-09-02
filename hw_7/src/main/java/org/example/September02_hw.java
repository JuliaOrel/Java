package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.entities.City;
import org.example.entities.Country;

import java.sql.*;
import java.util.Scanner;

public class September02_hw implements Runnable{
    private Connection connection;
    @Override
    public void run() {
        ConnectToDb();
//        try {
//            insertDataIntoCountriesTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            insertDataIntoCitiesTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        //migrate();
        try {
            displayMenu();
        } catch (SQLException e) {
            System.out.println("Something wrong"+e.getMessage());
        }
    }

    private void migrate(){
        String createCountriesTableSQL = "CREATE TABLE IF NOT EXISTS Countries ("
                + "CountryID INT AUTO_INCREMENT PRIMARY KEY,"
                + "CountryName VARCHAR(255) NOT NULL,"
                + "CapitalName VARCHAR(255) NOT NULL,"
                + "Population INT NOT NULL"
                + ")";

        // SQL-запрос для создания таблицы "Города"
        String createCitiesTableSQL = "CREATE TABLE IF NOT EXISTS Cities ("
                + "CityID INT AUTO_INCREMENT PRIMARY KEY,"
                + "CityName VARCHAR(255) NOT NULL,"
                + "CountryID INT,"
                + "Population INT,"
                + "Description TEXT,"
                + "FOREIGN KEY (CountryID) REFERENCES Countries(CountryID)"
                + ")";

        // Выполнить SQL-запросы для создания таблиц
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createCountriesTableSQL);
            statement.executeUpdate(createCitiesTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
}

    private void insertDataIntoCountriesTable() throws SQLException {
        String insertCountrySQL = "INSERT INTO Countries (CountryName, CapitalName, Population) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCountrySQL)) {
            preparedStatement.setString(1, "USA");
            preparedStatement.setString(2, "Washington, D.C.");
            preparedStatement.setInt(3, 331000000);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Ukraine");
            preparedStatement.setString(2, "Kyiev");
            preparedStatement.setInt(3, 36744360);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "China");
            preparedStatement.setString(2, "Beijing");
            preparedStatement.setInt(3, 1444216107);
            preparedStatement.executeUpdate();
        }
    }

    private void insertDataIntoCitiesTable() throws SQLException {
        String insertCitySQL = "INSERT INTO Cities (CityName, CountryID, Population, Description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCitySQL)) {
            preparedStatement.setString(1, "New York");
            preparedStatement.setInt(2, 1); // Соответствует США
            preparedStatement.setInt(3, 8398748);
            preparedStatement.setString(4, "The largest city in the USA.");

            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Mykolaiv");
            preparedStatement.setInt(2, 2); // Соответствует России
            preparedStatement.setInt(3, 486267);
            preparedStatement.setString(4, "Some notable landmarks in the city include the Mykolaiv Regional Academic Drama Theater, the Admiral Makarov National University of Shipbuilding, and the Monument to Shipbuilders..");

            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Shanghai");
            preparedStatement.setInt(2, 3); // Соответствует Китаю
            preparedStatement.setInt(3, 24256800);
            preparedStatement.setString(4, "One of China's major financial hubs.");

            preparedStatement.executeUpdate();
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
            System.out.println("5. Display all capitals");
            System.out.println("6. Display capital of the country");
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
                    dispalyAllCountries();
                    break;
                case 4:
                    displayCitiesByCountry();
                    break;
                case 5:
                    displayAllCapitals();
                    break;
                case 6:
                    displayCapitalCountry();
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

    private void dispalyAllCountries(){
        String sqlQuery = "SELECT * FROM countries"; // countries - это название вашей таблицы с информацией о странах

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Здесь вы можете обработать результаты запроса и вывести информацию о каждой стране
                String countryName = resultSet.getString("CountryName");
                String capital = resultSet.getString("CapitalName");
                // Добавьте другие поля, если они есть в вашей таблице
                System.out.println("Country: " + countryName);
                System.out.println("Capital: " + capital);
                System.out.println(); // Добавьте пустую строку для разделения
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCitiesByCountry() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input country: ");
        String CountryName=scanner.next();
        String query = "SELECT cities.CityName FROM cities " +
                "INNER JOIN countries ON cities.CountryId = countries.CountryID " +
                "WHERE countries.CountryName = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, CountryName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String cityName = resultSet.getString("CityName");
                    System.out.println("City: " + cityName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayAllCapitals(){
        String sqlQuery = "SELECT CapitalName FROM countries"; // countries - это название вашей таблицы с информацией о странах

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Здесь вы можете обработать результаты запроса и вывести информацию о каждой стране
                //String countryName = resultSet.getString("CountryName");
                String capital = resultSet.getString("CapitalName");
                // Добавьте другие поля, если они есть в вашей таблице
                //System.out.println("Country: " + countryName);
                System.out.println("Capital: " + capital);
                System.out.println(); // Добавьте пустую строку для разделения
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void displayCapitalCountry(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input country: ");
        String CountryName=scanner.next();
        String query = "SELECT CapitalName FROM countries WHERE CountryName = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, CountryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String capitalName = resultSet.getString("CapitalName");
                System.out.println("Capital of " + CountryName + " is " + capitalName);
            } else {
                System.out.println("Country not found: " + CountryName);
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
                System.out.println("1. Display 2 countries with the biggest population");
                System.out.println("2. Display 2 countries with the smallest population");
                System.out.println("3. Average population in the city of the country");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayTwoCountriesBiggestPopul();
                        break;
                    case 2:
                        displayTwoCountriesSmallesttPopul();
                        break;
                    case 3:
                        displayAveragePopulationForCountry();
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

    private void displayTwoCountriesBiggestPopul(){
        String query = "SELECT CountryName, Population FROM countries ORDER BY Population DESC LIMIT 2";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String countryName = resultSet.getString("CountryName");
                int population = resultSet.getInt("Population");
                System.out.println(countryName + ": " + population + " inhabitants");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayTwoCountriesSmallesttPopul(){
        String query = "SELECT CountryName, Population FROM countries ORDER BY Population ASC LIMIT 2";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String countryName = resultSet.getString("CountryName");
                int population = resultSet.getInt("Population");
                System.out.println(countryName + ": " + population + " inhabitants");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayAveragePopulationForCountry(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Input country: ");
        String CountryName=scanner.next();
        String query = "SELECT AVG(cities.Population) AS average_population " +
                "FROM cities " +
                "INNER JOIN countries ON cities.CountryID = countries.CountryID " +
                "WHERE countries.CountryName = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, CountryName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double averagePopulation = resultSet.getDouble("average_population");
                System.out.println("Среднее количество жителей в городах " + CountryName + ": " + averagePopulation);
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
                System.out.println("1. Add row country");
                System.out.println("2. Add row city");
                System.out.println("3. Delete row");
                System.out.println("4. Update row");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addCountry();
                        break;
                    case 2:
                        addCity();
                        break;
                    case 3:
                        //func();
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

    private void addCountry(){
        //City newCity = new City();
        Scanner scanner=new Scanner(System.in);
        Country newCountry = new Country();
        System.out.println("Input country name");
        newCountry.setCountryName(scanner.next());
        System.out.println("Input capital name");
        newCountry.setCapitalName(scanner.next());
        System.out.println("Input population");
        newCountry.setPopulation((scanner.nextInt()));
        try {
            // Вставляем данные в таблицу стран
            String insertCountrySQL = "INSERT INTO countries (CountryName, CapitalName, Population) VALUES (?, ?,?)";
            PreparedStatement countryStatement = connection.prepareStatement(insertCountrySQL, Statement.RETURN_GENERATED_KEYS);
            countryStatement.setString(1, newCountry.getCountryName());
            countryStatement.setString(2, newCountry.getCapitalName());
            countryStatement.setInt(3, newCountry.getPopulation());
            countryStatement.executeUpdate();


            // Получаем сгенерированный ID страны
            ResultSet generatedKeys = countryStatement.getGeneratedKeys();
            int countryId = -1;
            if (generatedKeys.next()) {
                countryId = generatedKeys.getInt(1);
            }

            System.out.println("Данные о стране успешно добавлены. countryID: " + countryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        answer();


    }
    private void answer(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do you want add note to table cities");

        while (true) {
            System.out.println("Make your choice:");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("3. Exit");

            int answer=scanner.nextInt();

            switch (answer) {
                case 1:
                    addCity();
                    break;
                case 2:
                    return;
                case 3:
                    return; // Выход из метода
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
            }
        }
    }

    private void addCity(){
        Scanner scanner=new Scanner(System.in);
        City newCity = new City();
        System.out.println("Input city name");
        newCity.setCityName(scanner.next());
        System.out.println("Input country id");
        newCity.setCountryId(scanner.nextInt());
        System.out.println("Input population");
        newCity.setPopulation(scanner.nextInt());
        System.out.println("Input description");
        newCity.setDescription(scanner.next());
        try {
            // Вставляем данные в таблицу стран
            String insertCountrySQL = "INSERT INTO cities (CityName, CountryID, Population, Description) VALUES (?, ?,?,?)";
            PreparedStatement cityStatement = connection.prepareStatement(insertCountrySQL, Statement.RETURN_GENERATED_KEYS);
            cityStatement.setString(1, newCity.getCityName());
            cityStatement.setInt(2, newCity.getCountryId());
            cityStatement.setInt(3, newCity.getPopulation());
            cityStatement.setString(4, newCity.getDescription());
            cityStatement.executeUpdate();


            // Получаем сгенерированный ID страны
            ResultSet generatedKeys = cityStatement.getGeneratedKeys();
            int cityID = -1;
            if (generatedKeys.next()) {
                cityID = generatedKeys.getInt(1);
            }

            System.out.println("Данные о стране успешно добавлены. countryID: " + cityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
