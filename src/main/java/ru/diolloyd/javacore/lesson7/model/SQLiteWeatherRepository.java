package ru.diolloyd.javacore.lesson7.model;

import ru.diolloyd.javacore.lesson7.AppGlobalState;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SQLiteWeatherRepository implements IWeatherRepository {

    @Override
    public List<CurrentWeather> getAllData() {
        Statement statement = AppGlobalState.getStatement();
        List<CurrentWeather> result = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM weather ORDER BY date DESC");
            while (rs.next()) {
                result.add(new CurrentWeather(
                        rs.getString(2),
                        Instant.ofEpochSecond(rs.getLong(3)),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void saveWeatherObject(CurrentWeather currentWeather) {
        PreparedStatement insertOne = AppGlobalState.getInsertWeatherPreparedStatement();
        try {
//            "INSERT INTO weather (location, date, temp, unit, precipitation) VALUES (?,?,?,?,?);"
            insertOne.setString(1, currentWeather.getLocation());
            insertOne.setLong(2, currentWeather.getDate().getEpochSecond());
            insertOne.setInt(3, currentWeather.getTemperature());
            insertOne.setString(4, currentWeather.getUnit());
            insertOne.setString(5, currentWeather.getPrecipitation());
            insertOne.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
