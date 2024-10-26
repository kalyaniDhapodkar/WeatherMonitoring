package com.weatherapp.model;

public class WeatherAlert {
    private String city;
    private String condition;
    private String message;
    private AlertSeverity severity;
    private long timestamp;

    public enum AlertSeverity {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public WeatherAlert(String city, String condition, String message, 
                       AlertSeverity severity) {
        this.city = city;
        this.condition = condition;
        this.message = message;
        this.severity = severity;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public AlertSeverity getSeverity() { return severity; }
    public void setSeverity(AlertSeverity severity) { this.severity = severity; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}