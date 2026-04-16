package com.example.smartcampus.model;

import java.util.ArrayList;
import java.util.List;

public class Sensor {

    private int id;
    private String type;
    private String status;
    private double currentValue;
    private int roomId;

    // NEW: readings history
    private List<SensorReading> readings = new ArrayList<>();

    public Sensor() {}

    public Sensor(int id, String type, String status, double currentValue, int roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentValue = currentValue;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public int getRoomId() {
        return roomId;
    }

    public List<SensorReading> getReadings() {
        return readings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setReadings(List<SensorReading> readings) {
        this.readings = readings;
    }
}