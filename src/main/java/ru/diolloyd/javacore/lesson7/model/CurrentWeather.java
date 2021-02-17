package ru.diolloyd.javacore.lesson7.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class CurrentWeather {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault());
    private String location;
    private Instant date;
    private int temperature;
    private String unit;
    private String precipitation;

    public CurrentWeather(String location, Instant date, int temperature, String unit, String precipitation) {
        this.location = location;
        this.date = date;
        this.temperature = temperature;
        this.unit = unit;
        this.precipitation = precipitation;
    }

    public CurrentWeather(String location, JsonNode jsonNode) {
        this(
                location,
                Instant.ofEpochSecond(jsonNode.at("/EpochTime").asLong()),
                jsonNode.at("/Temperature/Metric/Value").asInt(),
                jsonNode.at("/Temperature/Metric/Unit").asText(),
                jsonNode.at("/WeatherText").asText());
    }

    public int getTemperature() {
        return temperature;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public Instant getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("%s в %s было %d\u00B0%s %s", FORMATTER.format(date), location, temperature, unit, precipitation);
    }
}
