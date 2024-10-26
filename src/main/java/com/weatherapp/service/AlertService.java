package com.weatherapp.service;

import com.weatherapp.model.WeatherAlert;
import com.weatherapp.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AlertService {
    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);
    
    private final double HIGH_TEMP_THRESHOLD = 35.0;
    private final double LOW_TEMP_THRESHOLD = 0.0;
    private final double HIGH_WIND_THRESHOLD = 20.0;
    private final double HIGH_HUMIDITY_THRESHOLD = 80.0;

    public List<WeatherAlert> checkForAlerts(WeatherData weatherData) {
        List<WeatherAlert> alerts = new ArrayList<>();

        // Check temperature
        if (weatherData.getTemperature() > HIGH_TEMP_THRESHOLD) {
            alerts.add(new WeatherAlert(
                weatherData.getCity(),
                "High Temperature",
                "Temperature exceeds " + HIGH_TEMP_THRESHOLD + "°C",
                WeatherAlert.AlertSeverity.HIGH
            ));
        } else if (weatherData.getTemperature() < LOW_TEMP_THRESHOLD) {
            alerts.add(new WeatherAlert(
                weatherData.getCity(),
                "Low Temperature",
                "Temperature below " + LOW_TEMP_THRESHOLD + "°C",
                WeatherAlert.AlertSeverity.MEDIUM
            ));
        }

        // Check wind speed
        if (weatherData.getWindSpeed() > HIGH_WIND_THRESHOLD) {
            alerts.add(new WeatherAlert(
                weatherData.getCity(),
                "High Wind Speed",
                "Wind speed exceeds " + HIGH_WIND_THRESHOLD + " m/s",
                WeatherAlert.AlertSeverity.HIGH
            ));
        }

        // Check humidity
        if (weatherData.getHumidity() > HIGH_HUMIDITY_THRESHOLD) {
            alerts.add(new WeatherAlert(
                weatherData.getCity(),
                "High Humidity",
                "Humidity exceeds " + HIGH_HUMIDITY_THRESHOLD + "%",
                WeatherAlert.AlertSeverity.MEDIUM
            ));
        }

        if (!alerts.isEmpty()) {
            logger.info("Generated {} alerts for {}", alerts.size(), weatherData.getCity());
        }

        return alerts;
    }
}