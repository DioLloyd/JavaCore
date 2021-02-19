package ru.diolloyd.javacore.lesson7.model;

import com.fasterxml.jackson.databind.JsonNode;

public class DailyForecast {
    private String date;
    private String weatherText;
    private int minTemperature;
    private int maxTemperature;

    public DailyForecast(String date, String weatherText, int minTemperature, int maxTemperature) {
        this.date = date;
        this.weatherText = weatherText;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public DailyForecast(JsonNode jsonNode){
        this.date = jsonNode.at("/Date").asText().substring(0, 10);
        this.weatherText = jsonNode.at("/Day/IconPhrase").asText();
        this.minTemperature = jsonNode.at("/Temperature/Minimum/Value").asInt();
        this.maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asInt();
    }

    public String getDate() {
        return date;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }
}
