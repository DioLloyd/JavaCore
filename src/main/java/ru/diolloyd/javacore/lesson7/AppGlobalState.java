package ru.diolloyd.javacore.lesson7;

import ru.diolloyd.javacore.lesson7.model.City;

public class AppGlobalState {

    private static AppGlobalState instance;

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getApiKey() {
        return System.getenv("ACCUWEATHER_API_KEY");
    }

    private AppGlobalState(){}

    public static AppGlobalState getInstance() {
        if (instance == null) {
            instance = new AppGlobalState();
        }
        return instance;
    }
}
