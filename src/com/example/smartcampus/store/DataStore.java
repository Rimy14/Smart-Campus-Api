package com.example.smartcampus.store;

import com.example.smartcampus.model.Room;
import com.example.smartcampus.model.Sensor;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    // ROOMS
    public static Map<Integer, Room> rooms = new HashMap<>();
    public static int roomIdCounter = 1;

    // SENSORS (YOU WERE MISSING THIS ❗)
    public static Map<Integer, Sensor> sensors = new HashMap<>();
    public static int sensorIdCounter = 1;
}