package ru.diolloyd.javacore.lesson7.model;

import java.io.IOException;

public interface IWeatherProvider {

    CurrentWeather getCurrentWeather(String cityKey) throws IOException;
    WeatherResponse getWeatherOnFiveDays(String cityKey) throws IOException;

}
