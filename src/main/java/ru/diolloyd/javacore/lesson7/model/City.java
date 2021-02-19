package ru.diolloyd.javacore.lesson7.model;

import java.util.Objects;

public class City {
    private String key;
    private String name;
    private String country;

    public City(String key, String name, String country) {
        this.key = key;
        this.name = name;
        this.country = country;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return key.equals(city.key) && name.equals(city.name) && country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, country);
    }
}
