package com.weatherapp.service;

import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import com.weatherapp.model.WeatherData;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public DatabaseService() {
        // Initialize MongoDB connection
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("weatherDB");
        this.collection = database.getCollection("weatherData");
    }

    public void saveWeatherData(WeatherData data) {
        Document doc = new Document()
                .append("city", data.getCity())
                .append("temperature", data.getTemperature())
                .append("humidity", data.getHumidity())
                .append("windSpeed", data.getWindSpeed())
                .append("timestamp", data.getTimestamp());
        
        collection.insertOne(doc);
    }

    public WeatherData getLatestWeatherData(String city) {
        Document query = new Document("city", city);
        Document result = collection.find(query)
                .sort(Sorts.descending("timestamp"))
                .first();

        if (result == null) {
            return null;
        }

        return new WeatherData(
                result.getString("city"),
                result.getDouble("temperature"),
                result.getDouble("humidity"),
                result.getDouble("windSpeed"),
                result.getLong("timestamp")
        );
    }

    public List<WeatherData> getHistoricalData(String city, long startTime, long endTime) {
        Document query = new Document("city", city)
                .append("timestamp", new Document("$gte", startTime).append("$lte", endTime));
        
        List<WeatherData> historicalData = new ArrayList<>();
        collection.find(query)
                .sort(Sorts.ascending("timestamp"))
                .forEach(doc -> historicalData.add(new WeatherData(
                        doc.getString("city"),
                        doc.getDouble("temperature"),
                        doc.getDouble("humidity"),
                        doc.getDouble("windSpeed"),
                        doc.getLong("timestamp")
                )));
        
        return historicalData;
    }

    public void clearDatabase() {
        collection.deleteMany(new Document());
    }

    // Make sure to close the MongoDB connection when done
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}