package ru.diolloyd.javacore.lesson7.model;

import java.io.IOException;

public interface IWeatherProvider {

    void getCurrentWeather(String cityKey) throws IOException;
    void getWeatherOnFiveDays(String cityKey) throws IOException;

}
