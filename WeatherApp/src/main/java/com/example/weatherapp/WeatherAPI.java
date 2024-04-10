package com.example.weatherapp;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherAPI {
    private static final String API_KEY = "f6a862fdb92c9f9a83ac3a137f5c852b";

       public static JSONObject getWeatherData(String cityName) throws Exception {

        String API_URL = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&APPID="+API_KEY;

           URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } else {
            throw new Exception("API request failed with response code: " + responseCode);
        }
    }
}
