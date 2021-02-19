package ru.diolloyd.javacore.lesson7.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.diolloyd.javacore.lesson7.AppGlobalState;

import java.io.IOException;

public class AccuWeatherProvider implements IWeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String CONDITIONS_PATH = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = AppGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CurrentWeather getCurrentWeather(String cityKey) throws IOException {
        //http://dataservice.accuweather.com/currentconditions/v1/27497?apikey={{accuweatherApiKey}}

        HttpUrl getWeatherUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(CONDITIONS_PATH)
                .addPathSegment(API_VERSION)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .build();

        Request getWeatherRequest = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(getWeatherUrl)
                .build();

        Response response = client.newCall(getWeatherRequest).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Ошибка сети\n");
        }

        String jsonBodyResponse = response.body().string();

        JsonNode jsonNode = objectMapper.readTree(jsonBodyResponse).get(0);
        City city = AppGlobalState.getInstance().getCity();
        String location = city.getName() + ", " + city.getCountry();

        CurrentWeather weather = new CurrentWeather(location, jsonNode);
        return weather;
    }

    @Override
    public WeatherResponse getWeatherOnFiveDays(String cityKey) throws IOException {
        // http://dataservice.accuweather.com/forecasts/v1/daily/5day/{locationKey}?apikey={{accuweatherApiKey}}

        HttpUrl getWeatherUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("forecasts")
                .addPathSegment(API_VERSION)
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        Request getWeatherRequest = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(getWeatherUrl)
                .build();

        Response response = client.newCall(getWeatherRequest).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Ошибка сети\n");
        }

        String jsonBodyResponse = response.body().string();

        JsonNode rootNode = objectMapper.readTree(jsonBodyResponse);
        return new WeatherResponse(rootNode);


    }
}
