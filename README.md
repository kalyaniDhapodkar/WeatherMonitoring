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

git clone https://github.com/kalyaniDhapodkar/WeatherMonitoring.git<br/>
cd WeatherMonitoring

2. Configure the API key: <br/>
Sign up at OpenWeatherMap to get an API key <br/>
Open src/main/java/com/weatherapp/service/WeatherService.java <br/>
Replace YOUR_API_KEY with your actual API key: <br/>

private static final String API_KEY = "your_actual_api_key";<br/>

3. Start MongoDB:
   mongod --dbpath /(path to data directory)<br/>
   Or<br/>
   net start MongoDB<br/>

5. Build the application:<br/>
   mvn clean package

## Running the Application
Run the application using: <br/>
java -jar target/weather-monitoring-system-1.0-SNAPSHOT-jar-with-dependencies.jar

## Usage

1. Getting Current Weather <br/>
Enter a city name in the text field <br/>
Click "Get Current Weather" <br/>
View the current temperature, humidity, and wind speed<br/>

2. Viewing Historical Data <br/>
Enter a city name <br/>
Click "Get Weather History" <br/>
View weather data from the past 24 hours <br/>

## Sample Inputs
## Popular Cities:

London<br/>
New York<br/>
Tokyo<br/>
Paris<br/>
Mumbai<br/>
Sydney<br/>

## Dependencies<br/>
MongoDB Driver: For database operations<br/>
org.json: For JSON parsing<br/>
JUnit: For unit testing<br/>
Swing: For GUI (included in JDK)<br/>

## Configuration<br/>
MongoDB Configuration<br/>
Default host: localhost<br/>
Default port: 27017<br/>
Database name: weatherdb<br/>
Collection name: weather_data<br/>

## API Configuration<br/>
Base URL: http://api.openweathermap.org/data/2.5/weather<br/>
Units: Metric (Celsius)<br/>

## Error Handling<br/>
The application handles various error scenarios:<br/>
Invalid city names<br/>
Network connectivity issues<br/>
Database connection problems<br/>
Empty input validation<br/>

## Troubleshooting<br/>
Common issues and solutions:<br/>

## 1. API Key Issues<br/>
Verify API key is correctly set<br/>
Check API call limits<br/>
Ensure internet connectivity<br/>

## 2. MongoDB Issues<br/>
Verify MongoDB is running<br/>
Check MongoDB connection string<br/>
Ensure proper permissions<br/>

## 3. Build Issues<br/>
Clean and rebuild project<br/>
Verify Maven dependencies<br/>
Check Java version compatibility<br/>

## Contact<br/>
Name - dhapodkarkalyani@email.com <br/>
Project Link:  https://github.com/kalyaniDhapodkar/WeatherMonitoring.git
