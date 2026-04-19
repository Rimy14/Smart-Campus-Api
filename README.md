# Smart Campus API

## 📌 Overview

The Smart Campus API is a RESTful web service developed using Java, JAX-RS (Jersey), and the Grizzly HTTP server.
It manages rooms, sensors, and sensor readings within a smart campus environment.

The API follows RESTful principles and provides structured JSON responses with proper HTTP status codes.

---

## 🚀 Base URL

```bash
http://localhost:8080/api/v1/
```

---

# 📊 API Endpoints

## 🔹 Discovery

```bash
GET /api/v1/
```

## 🔹 Rooms

```bash
POST /rooms
GET /rooms
GET /rooms/{id}
DELETE /rooms/{id}
```

## 🔹 Sensors

```bash
POST /sensors
GET /sensors
GET /sensors?type=Temperature
```

## 🔹 Sensor Readings

```bash
POST /sensors/{id}/readings
GET /sensors/{id}/readings
```

---

# ⚙️ How to Build and Run

## 🔧 Step-by-step instructions

1. Clone the repository:

```bash
git clone https://github.com/Rimy14/Smart-Campus-Api.git
```

2. Open the project in **NetBeans**

3. Ensure Maven dependencies are downloaded automatically

4. Run the project using NetBeans (Run ▶️)

5. The server will start at:

```bash
http://localhost:8080/api/v1/
```

---

# 🧪 Sample curl Commands

## 1. Get API info

```bash
curl http://localhost:8080/api/v1/
```

## 2. Create a room

```bash
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"name\":\"Lab A\",\"capacity\":30}"
```

## 3. Get all rooms

```bash
curl http://localhost:8080/api/v1/rooms
```

## 4. Create a sensor

```bash
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":0,\"roomId\":1}"
```

## 5. Add sensor reading

```bash
curl -X POST http://localhost:8080/api/v1/sensors/1/readings \
-H "Content-Type: application/json" \
-d "{\"value\":25.5}"
```

---

# ⚠️ Error Handling

The API returns structured JSON responses for errors:

### 409 Conflict

```json
{
  "status": 409,
  "message": "Room has active sensors"
}
```

### 422 Unprocessable Entity

```json
{
  "status": 422,
  "message": "Room with id 999 does not exist"
}
```

### 403 Forbidden

```json
{
  "status": 403,
  "message": "Sensor is under maintenance and cannot accept readings"
}
```

### 500 Internal Server Error

```json
{
  "status": 500,
  "message": "An unexpected internal server error occurred"
}
```

---


