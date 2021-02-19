package ru.diolloyd.javacore.lesson7.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherResponse {
    private final String unit;
    private List<DailyForecast> dailyForecasts;

    public WeatherResponse(JsonNode rootNode) {
        JsonNode dailyForecasts = rootNode.at("/DailyForecasts");
        Iterator<JsonNode> elements = dailyForecasts.elements();
        this.dailyForecasts = new ArrayList<>();
        while (elements.hasNext()) {
            JsonNode node = elements.next();
            this.dailyForecasts.add(new DailyForecast(node));
        }
        unit = dailyForecasts.get(0).at("/Temperature/Minimum/Unit").asText();
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public String getUnit() {
        return unit;
    }
}
