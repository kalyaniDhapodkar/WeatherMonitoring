package com.weatherapp.ui;

import com.weatherapp.model.WeatherData;
import com.weatherapp.service.DatabaseService;
import com.weatherapp.service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class WeatherUI extends JFrame {
    private final WeatherService weatherService;
    private final DatabaseService databaseService;
    private JTextField cityField;
    private JTextArea resultArea;

    public WeatherUI() {
        weatherService = new WeatherService();
        databaseService = new DatabaseService();

        // Set up the main frame
        setTitle("Weather Monitoring System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField(20);
        JButton getCurrentButton = new JButton("Get Current Weather");
        JButton getHistoryButton = new JButton("Get Weather History");

        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(getCurrentButton);
        inputPanel.add(getHistoryButton);

        // Create result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Add action listeners
        getCurrentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCurrentWeather();
            }
        });

        getHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getWeatherHistory();
            }
        });
    }

    private void getCurrentWeather() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            WeatherData currentWeather = weatherService.getCurrentWeather(city);
            if (currentWeather != null) {
                databaseService.saveWeatherData(currentWeather);
                displayWeatherData(currentWeather);
            } else {
                resultArea.setText("Could not fetch weather data for " + city);
            }
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    private void getWeatherHistory() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Get data for last 24 hours
            long endTime = System.currentTimeMillis();
            long startTime = endTime - (24 * 60 * 60 * 1000);
            List<WeatherData> historicalData = databaseService.getHistoricalData(city, startTime, endTime);

            if (historicalData.isEmpty()) {
                resultArea.setText("No historical data found for " + city);
            } else {
                displayHistoricalData(historicalData);
            }
        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    private void displayWeatherData(WeatherData data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Current Weather for ").append(data.getCity()).append("\n");
        sb.append("Temperature: ").append(data.getTemperature()).append("°C\n");
        sb.append("Humidity: ").append(data.getHumidity()).append("%\n");
        sb.append("Wind Speed: ").append(data.getWindSpeed()).append(" m/s\n");
        sb.append("Time: ").append(sdf.format(new Date(data.getTimestamp()))).append("\n");

        resultArea.setText(sb.toString());
    }

    private void displayHistoricalData(List<WeatherData> historicalData) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Historical Weather Data for ").append(historicalData.get(0).getCity()).append("\n\n");

        for (WeatherData data : historicalData) {
            sb.append("Time: ").append(sdf.format(new Date(data.getTimestamp()))).append("\n");
            sb.append("Temperature: ").append(data.getTemperature()).append("°C\n");
            sb.append("Humidity: ").append(data.getHumidity()).append("%\n");
            sb.append("Wind Speed: ").append(data.getWindSpeed()).append(" m/s\n");
            sb.append("------------------------\n");
        }

        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        // Run the UI in the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherUI().setVisible(true);
            }
        });
    }
}