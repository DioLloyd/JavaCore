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
    public void getCurrentWeather(String cityKey) throws IOException {
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
        Integer temperature = objectMapper.readTree(jsonBodyResponse).get(0).at("/Temperature/Metric/Value").asInt();
        String unit = objectMapper.readTree(jsonBodyResponse).get(0).at("/Temperature/Metric/Unit").asText();
        String precipitation = objectMapper.readTree(jsonBodyResponse).get(0).at("/WeatherText").asText();
        System.out.printf("Текущая погода %d %s\nПогодные условия: %s\n", temperature, unit, precipitation);
    }

    @Override
    public void getWeatherOnFiveDays(String cityKey) throws IOException {
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
        WeatherResponse weatherResponse = new WeatherResponse(rootNode);

        for (DailyForecast df : weatherResponse.getDailyForecasts()) {
            System.out.printf("В городе %s на дату %s ожидается: %s, температура от %d%s до %d%s%n",
                    AppGlobalState.getInstance().getCity().getName(), df.getDate(), df.getWeatherText(),
                    df.getMinTemperature(), weatherResponse.getUnit(),
                    df.getMaxTemperature(), weatherResponse.getUnit());

        }
    }
}
