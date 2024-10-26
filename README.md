# Weather Monitoring System

A Java-based desktop application that allows users to fetch, store, and monitor weather data for different cities using the OpenWeatherMap API and MongoDB for data persistence.

## Features

- Real-time weather data fetching
- Historical weather data storage and retrieval
- User-friendly graphical interface
- Support for global cities
- Data persistence using MongoDB
- Error handling and validation

## Prerequisites

Before running the application, ensure you have the following installed:

- Java JDK 11 or higher
- Maven 3.6 or higher
- MongoDB 4.4 or higher
- OpenWeatherMap API key

## Installation

1. Clone the repository:

git clone https://github.com/kalyaniDhapodkar/WeatherMonitoring.git
cd WeatherMonitoring

2. Configure the API key:
Sign up at OpenWeatherMap to get an API key
Open src/main/java/com/weatherapp/service/WeatherService.java
Replace YOUR_API_KEY with your actual API key:

private static final String API_KEY = "your_actual_api_key";

3. Start MongoDB:
   mongod --dbpath /(path to data directory)
   Or
   net start MongoDB

4. Build the application:
   mvn clean package

## Running the Application
Run the application using:
java -jar target/weather-monitoring-system-1.0-SNAPSHOT-jar-with-dependencies.jar

## Usage

1. Getting Current Weather
Enter a city name in the text field
Click "Get Current Weather"
View the current temperature, humidity, and wind speed

2. Viewing Historical Data
Enter a city name
Click "Get Weather History"
View weather data from the past 24 hours

## Sample Inputs
# Popular Cities:

London
New York
Tokyo
Paris
Mumbai
Sydney

## Dependencies
MongoDB Driver: For database operations
org.json: For JSON parsing
JUnit: For unit testing
Swing: For GUI (included in JDK)

## Configuration
MongoDB Configuration
Default host: localhost
Default port: 27017
Database name: weatherdb
Collection name: weather_data

## API Configuration
Base URL: http://api.openweathermap.org/data/2.5/weather
Units: Metric (Celsius)

## Error Handling
The application handles various error scenarios:
Invalid city names
Network connectivity issues
Database connection problems
Empty input validation

## Troubleshooting
Common issues and solutions:

## 1. API Key Issues
Verify API key is correctly set
Check API call limits
Ensure internet connectivity

## 2. MongoDB Issues
Verify MongoDB is running
Check MongoDB connection string
Ensure proper permissions

## 3. Build Issues
Clean and rebuild project
Verify Maven dependencies
Check Java version compatibility

## Contact
Name - dhapodkarkalyani@email.com 
Project Link:  https://github.com/kalyaniDhapodkar/WeatherMonitoring.git
