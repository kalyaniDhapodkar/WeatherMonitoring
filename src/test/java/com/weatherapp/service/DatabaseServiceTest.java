package com.weatherapp.service;

import com.weatherapp.model.WeatherData;
import org.junit.jupiter.api.*;
import java.util.List;  // Add this import
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseServiceTest {
    private static DatabaseService databaseService;

    @BeforeAll
    static void setUp() {
        databaseService = new DatabaseService();
        databaseService.clearDatabase();
    }

    @AfterAll
    static void tearDown() {
        databaseService.clearDatabase();
        databaseService.close();
    }

    @Test
    void testSaveAndRetrieveWeatherData() {
        // Create test weather data
        WeatherData testData = new WeatherData(
            "TestCity",
            25.5,  // temperature
            60.0,  // humidity
            10.0,  // windSpeed
            System.currentTimeMillis()
        );

        // Save the data
        databaseService.saveWeatherData(testData);

        // Retrieve the data
        WeatherData retrievedData = databaseService.getLatestWeatherData("TestCity");

        // Verify the data
        assertNotNull(retrievedData);
        assertEquals("TestCity", retrievedData.getCity());
        assertEquals(25.5, retrievedData.getTemperature(), 0.01);
        assertEquals(60.0, retrievedData.getHumidity(), 0.01);
        assertEquals(10.0, retrievedData.getWindSpeed(), 0.01);
    }

    @Test
    void testGetHistoricalData() {
        // Create and save multiple weather data points
        long currentTime = System.currentTimeMillis();
        
        WeatherData data1 = new WeatherData("TestCity", 20.0, 55.0, 8.0, currentTime - 3600000);
        WeatherData data2 = new WeatherData("TestCity", 22.0, 58.0, 9.0, currentTime);
        
        databaseService.saveWeatherData(data1);
        databaseService.saveWeatherData(data2);

        // Retrieve historical data
        long startTime = currentTime - 7200000; // 2 hours ago
        long endTime = currentTime + 3600000;   // 1 hour from now
        
        List<WeatherData> historicalData = databaseService.getHistoricalData("TestCity", startTime, endTime);

        // Verify the historical data
        assertNotNull(historicalData);
        assertTrue(historicalData.size() >= 2);
        
        // Verify the data is ordered by timestamp
        assertTrue(historicalData.get(0).getTimestamp() <= historicalData.get(1).getTimestamp());
    }
}