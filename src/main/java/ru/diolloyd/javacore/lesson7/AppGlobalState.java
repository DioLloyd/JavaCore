package ru.diolloyd.javacore.lesson7;

import ru.diolloyd.javacore.lesson7.model.City;

import java.sql.*;

public class AppGlobalState {
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static AppGlobalState instance;
    private City city;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement insertWeatherPreparedStatement;

    public static Statement getStatement() {
        return statement;
    }

    public static PreparedStatement getInsertWeatherPreparedStatement() {
        return insertWeatherPreparedStatement;
    }

    public String getDbName() {
        return "weather-app.db";
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getApiKey() {
        return System.getenv("ACCUWEATHER_API_KEY");
    }

    private AppGlobalState() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + getDbName());
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS weather (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "location TEXT NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "temp INT NOT NULL, " +
                    "unit TEXT NOT NULL, " +
                    "precipitation TEXT NOT NULL" +
                    ");");


            insertWeatherPreparedStatement = connection.prepareStatement(
                    "INSERT INTO weather (location, date, temp, unit, precipitation) VALUES (?,?,?,?,?);"
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
    }

    public static AppGlobalState getInstance() {
        if (instance == null) {
            instance = new AppGlobalState();
        }
        return instance;
    }
}
