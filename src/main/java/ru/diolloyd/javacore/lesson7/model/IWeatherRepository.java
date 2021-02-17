package ru.diolloyd.javacore.lesson7.model;

import java.util.List;

public interface IWeatherRepository {

    List<CurrentWeather> getAllData();

    void saveWeatherObject(CurrentWeather weather);
}
