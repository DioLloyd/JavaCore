package ru.diolloyd.javacore.lesson7.model;

import java.io.IOException;

public interface IWeatherProvider {

    CurrentWeather getCurrentWeather(String cityKey) throws IOException;
    void getWeatherOnFiveDays(String cityKey) throws IOException;

}
