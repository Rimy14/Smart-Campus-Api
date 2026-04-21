# Smart Campus API

## Overview

The Smart Campus API is a RESTful web service developed using Java, JAX-RS (Jersey), and Grizzly HTTP server.
It manages rooms, sensors, and sensor readings within a smart campus environment.

---

## Base URL

```bash
http://localhost:8080/api/v1/
```

---

## How to Build and Run

1. Clone the repository:

```bash
git clone https://github.com/Rimy14/Smart-Campus-Api.git
```

2. Open the project in NetBeans
3. Allow Maven dependencies to download
4. Run the project
5. Access:

```bash
http://localhost:8080/api/v1/
```

---

## Sample curl Commands

```bash
# 1. Get API info
curl http://localhost:8080/api/v1/
```

Sends a GET request to the discovery endpoint and returns API metadata such as version and available resources.

---

```bash
# 2. Create a room
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{
  "name": "Lab A",
  "capacity": 30
}'
```

Sends a POST request to create a new room with the specified name and capacity.

---

```bash
# 3. Get all rooms
curl http://localhost:8080/api/v1/rooms
```

Retrieves a list of all rooms currently stored in the system.

---

```bash
# 4. Create a sensor
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{
  "type": "Temperature",
  "status": "ACTIVE",
  "currentValue": 0,
  "roomId": 1
}'
```

Creates a new sensor and links it to an existing room using the provided roomId.

---

```bash
# 5. Add sensor reading
curl -X POST http://localhost:8080/api/v1/sensors/1/readings \
-H "Content-Type: application/json" \
-d '{
  "value": 25.5
}'
```

Adds a new reading to the specified sensor and updates its current value.


---

# Report: Answers to Coursework Questions

## PART 1 — Service Architecture & Setup

### Question 1:

In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

### Answer:

In JAX-RS, resource classes are request-scoped by default, meaning a new instance is created for each incoming request. This ensures that requests do not share the same object, improving thread safety. However, shared data structures like HashMaps are accessed by multiple threads. Developers must use synchronization or thread-safe collections to avoid race conditions. Without proper handling, concurrent updates can lead to inconsistent data or data loss.

---

### Question 2:

Why is the provision of “Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

### Answer:

HATEOAS allows API responses to include links to related resources, enabling clients to navigate dynamically. This reduces dependency on hardcoded URLs and static documentation. Clients can discover available actions at runtime. If the API changes, updated links guide the client correctly. This improves flexibility and maintainability.

---

## PART 2 — Room Management

### Question 1:

When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client side processing.

### Answer:

Returning only IDs reduces response size and saves bandwidth. However, the client must make additional requests to get full details. Returning full objects provides complete information in one response. This simplifies client logic but increases payload size. The choice depends on performance and usability needs.

---

### Question 2:

Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

### Answer:

Yes, DELETE is idempotent because repeating the same request results in the same final state. Once a room is deleted, it is removed from the system. Sending the request again does not change anything further. The API may return a “not found” response. This ensures safe retries without side effects.

---

## PART 3 — Sensor Operations & Linking

### Question 1:

We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

### Answer:

The @Consumes annotation specifies that the API expects JSON input. If a client sends data in another format, JAX-RS cannot process it. The framework returns a 415 Unsupported Media Type error. This ensures only valid formats are accepted. It also prevents parsing errors and maintains consistency.

---

### Question 2:

You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/v1/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections?

### Answer:

Query parameters are flexible and allow optional filtering without changing the URL structure. Multiple filters can be added easily. Path parameters are better for identifying specific resources. Using query parameters keeps URLs clean and scalable. It also follows REST design practices for searching collections.

---

## PART 4 — Deep Nesting with Sub-Resources

### Question 1:

Discuss the architectural benefits of the Sub-Resource Locator pattern. How
does delegating logic to separate classes help manage complexity in large APIs compared
to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive con-
troller class?

### Answer:

The Sub-Resource Locator pattern helps divide a large API into smaller, manageable components. Instead of handling all logic in one large controller, each sub-resource is managed by a separate class. This improves code readability and makes the structure easier to understand. It also reduces duplication and keeps responsibilities clearly separated. In large systems, this approach improves maintainability and allows developers to extend features without affecting other parts of the API.

---

## PART 5 — Error Handling & Logging

### Question 1:

Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

### Answer:

HTTP 422 is used when the request is syntactically correct but contains invalid or incorrect data. In this case, the JSON format is valid, but it refers to a resource that does not exist, such as an invalid roomId. A 404 error is used when the requested endpoint itself cannot be found. Therefore, 422 provides a more precise description of the problem. It helps clients understand that the issue is with the data, not the endpoint. This improves error clarity and API usability.

---

### Question 2:

From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

### Answer:

Exposing stack traces can reveal sensitive internal details such as file paths, class names, method structures, and libraries used in the system. Attackers can use this information to understand how the application is built. This makes it easier to identify weaknesses and exploit vulnerabilities. It may also expose configuration details or system structure. To prevent this, APIs should return generic error messages and log detailed errors internally.

---

### Question 3:

Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?

### Answer:

JAX-RS filters allow logging to be handled in a centralized way instead of repeating code in every method. This reduces duplication and keeps the codebase cleaner. It also ensures that all requests and responses are logged consistently. Filters make it easier to maintain and update logging behavior. They are especially useful for handling cross-cutting concerns like logging, authentication, and error handling in one place.
