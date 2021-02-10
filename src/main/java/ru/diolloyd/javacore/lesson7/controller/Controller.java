package ru.diolloyd.javacore.lesson7.controller;

import ru.diolloyd.javacore.lesson7.AppGlobalState;
import ru.diolloyd.javacore.lesson7.model.*;

import java.io.IOException;

public class Controller implements IController {

    ICityCodeProvider codeProvider = new AccuWeatherCityCodeProvider();
    IWeatherProvider weatherProvider = new AccuWeatherProvider();

    @Override
    public void onCityInput(String city) throws IOException {
        if (city.length() == 1) {
            throw new IOException("Недопустимо короткое название города");
        }

        codeProvider.getCodeByCityName(city);
    }

    @Override
    public void onCommandChosen(int selectedCommand) throws IOException {
        switch (selectedCommand) {
            case 1: {
                weatherProvider.getCurrentWeather(AppGlobalState.getInstance().getCity().getKey());
                break;
            }
            case 2: {
                weatherProvider.getWeatherOnFiveDays(AppGlobalState.getInstance().getCity().getKey());
                break;
            }
            default: {
                throw new IOException("Неверный ввод\n");
            }
        }
    }
}
