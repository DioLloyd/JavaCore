package ru.diolloyd.javacore.lesson6;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class Lesson6 {
    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weather.yandex.ru/v2/forecast?lat=59.989136&lon=30.301396")
                .addHeader("X-Yandex-API-Key", args[0])
                .build();
        Response response = client.newCall(request).execute();


        ResponseBody body = response.body();
        if(response.isSuccessful() && body != null) {

            String json = body.string();

            Gson gson = new Gson();
            Dataset dataset = gson.fromJson(json, Dataset.class);
            System.out.println("Город: " + dataset.getGeoObject().getLocality().getName());
            System.out.println("Район: " + dataset.getGeoObject().getDistrict().getName());
            System.out.println("Текущая погода: " + dataset.getFact().getTemp());
            System.out.println("По ощущениям: " + dataset.getFact().getFeelsLike());
        } else {
            System.out.println("Ошибка");
        }
    }
}
