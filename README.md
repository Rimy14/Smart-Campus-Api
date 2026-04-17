# Smart Campus API

## 📌 Overview

The Smart Campus API is a RESTful web service developed using Java, JAX-RS (Jersey), and Grizzly HTTP server.
It manages rooms, sensors, and sensor readings within a smart campus environment.

---

## 🚀 Base URL

```
http://localhost:8080/api/v1/
```

---

#  PART 1 — Service Architecture & Discovery

## 🔹 Endpoint

```
GET /api/v1/
```

### **Q1: Explain JAX-RS Resource Lifecycle**

In JAX-RS, a new instance of a resource class is created for each incoming request (request-scoped lifecycle).
This ensures thread safety since multiple requests do not share the same object instance. However, shared data structures such as HashMaps must be handled carefully to avoid race conditions.

---

### **Q2: What is HATEOAS and why is it useful?**

HATEOAS (Hypermedia as the Engine of Application State) allows API responses to include links to related resources.
This enables clients to dynamically navigate the API without relying on hardcoded URLs, improving flexibility and usability.

---

#  PART 2 — Room Management

## 🔹 Endpoints

```
POST /rooms
GET /rooms
GET /rooms/{id}
DELETE /rooms/{id}
```

### **Q3: Returning IDs vs Full Objects**

Returning only IDs reduces response size and improves performance, but requires additional API calls to fetch details.
Returning full objects provides complete information in a single response but increases payload size.

---

### **Q4: Why is DELETE idempotent?**

DELETE is idempotent because performing the same delete operation multiple times results in the same final state.
Once a resource is deleted, further DELETE requests will not change the system.

---

#  PART 3 — Sensor Operations

## 🔹 Endpoints

```
POST /sensors
GET /sensors
GET /sensors?type=Temperature
```

### **Q5: Purpose of @Consumes(MediaType.APPLICATION_JSON)**

The @Consumes annotation specifies that the API expects JSON input.
If a client sends data in another format, the request is rejected with a 415 Unsupported Media Type error.

---

### **Q6: QueryParam vs PathParam**

Query parameters are used for filtering (e.g., `/sensors?type=CO2`) and are more flexible.
Path parameters are used to identify specific resources (e.g., `/sensors/1`).

---

#  PART 4 — Sensor Readings (Sub-Resources)

## 🔹 Endpoints

```
POST /sensors/{id}/readings
GET /sensors/{id}/readings
```

### **Q7: Sub-Resource Locator Pattern**

This pattern delegates handling of sub-resources to separate classes.
It improves modularity, readability, and maintainability of the code.

---

### **Q8: Data Consistency**

When a new sensor reading is added, the sensor’s `currentValue` is updated.
This ensures that the latest reading is always reflected in the sensor data.

---

#  PART 5 — Error Handling

### **Q9: Why use 422 instead of 404?**

HTTP 422 is used when the request is valid but contains incorrect data (e.g., invalid roomId).
HTTP 404 is used when a resource does not exist. Therefore, 422 is more appropriate for validation errors.

---

### **Q10: Why should stack traces not be exposed?**

Stack traces reveal internal system details such as file paths and class names.
This information can be exploited by attackers, so a global exception handler is used to return a generic error message.

---

### **Q11: Why use filters for logging?**

Filters allow centralized logging of all requests and responses.
This avoids duplication and ensures consistent logging across the application.

---

## 🔹 Error Examples

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

# ⚙️ How to Run

1. Open project in NetBeans
2. Run the project
3. Access:

```
http://localhost:8080/api/v1/
```

---

# 👨‍💻 Author

Rimaz Nowfel
BSc (Hons) Computer Science
IIT Sri Lanka
