package com.weatherapp;

import com.weatherapp.ui.WeatherUI;
import javax.swing.SwingUtilities;

public class WeatherApp {
    public static void main(String[] args) {
        // Launch the Swing application
        SwingUtilities.invokeLater(() -> {
            try {
                WeatherUI weatherUI = new WeatherUI();
                weatherUI.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}