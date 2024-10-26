package com.weatherapp.service;

import com.weatherapp.model.WeatherData;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "de7340b12053ccb23b5f2a33e569c531"; // Replace with your OpenWeatherMap API key
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherData getCurrentWeather(String city) throws Exception {
        // Build the API URL
        String urlString = String.format("%s?q=%s&appid=%s&units=metric", API_URL, city, API_KEY);
        URL url = new URL(urlString);

        // Make HTTP request
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response
        JSONObject json = new JSONObject(response.toString());
        
        // Extract weather data
        double temperature = json.getJSONObject("main").getDouble("temp");
        double humidity = json.getJSONObject("main").getDouble("humidity");
        double windSpeed = json.getJSONObject("wind").getDouble("speed");
        long timestamp = System.currentTimeMillis();

        // Create and return WeatherData object
        return new WeatherData(city, temperature, humidity, windSpeed, timestamp);
    }
}