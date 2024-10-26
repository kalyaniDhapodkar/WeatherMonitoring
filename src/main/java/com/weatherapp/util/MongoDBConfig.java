package com.weatherapp.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConfig {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "weatherdb";

    public static MongoClient getMongoClient() {
        return MongoClients.create(CONNECTION_STRING);
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }
}