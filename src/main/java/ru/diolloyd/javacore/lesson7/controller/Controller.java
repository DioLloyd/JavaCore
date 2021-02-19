package ru.diolloyd.javacore.lesson7.controller;

import ru.diolloyd.javacore.lesson7.AppGlobalState;
import ru.diolloyd.javacore.lesson7.model.*;

import java.io.IOException;
import java.util.List;

public class Controller implements IController {

    ICityCodeProvider codeProvider = new AccuWeatherCityCodeProvider();
    IWeatherProvider weatherProvider = new AccuWeatherProvider();
    IWeatherRepository weatherRepository = new SQLiteWeatherRepository();

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
                CurrentWeather weather = weatherProvider.getCurrentWeather(AppGlobalState.getInstance().getCity().getKey());
                System.out.printf("Текущая погода %d %s\nПогодные условия: %s\n",
                        weather.getTemperature(), weather.getUnit(), weather.getPrecipitation());
                weatherRepository.saveWeatherObject(weather);
                break;
            }
            case 2: {
                WeatherResponse weatherResponse = weatherProvider.getWeatherOnFiveDays(AppGlobalState.getInstance().getCity().getKey());
                for (DailyForecast df : weatherResponse.getDailyForecasts()) {
                    System.out.printf("В городе %s на дату %s ожидается: %s, температура от %d%s до %d%s%n",
                            AppGlobalState.getInstance().getCity().getName(), df.getDate(), df.getWeatherText(),
                            df.getMinTemperature(), weatherResponse.getUnit(),
                            df.getMaxTemperature(), weatherResponse.getUnit());

                }
                break;
            }
            case 3: {
                List<CurrentWeather> allData = weatherRepository.getAllData();
                allData.forEach(System.out::println);
                break;
            }
            default: {
                throw new IOException("Неверный ввод\n");
            }
        }
    }
}
