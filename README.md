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

Using HATEOAS, the returned API response is allowed to have links to other related resources, thus allowing for dynamic navigation by the client application. It eliminates the need for static URLs as well as static documentation for each request to be made by the client. This improves flexibility and maintainability.

---

## PART 2 — Room Management

### Question 1:

When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client side processing.

### Answer:

Sending back only IDs decreases the size of the response and minimizes network traffic. Nevertheless, the client is required to issue further requests for obtaining additional details. Sending back the whole object ensures that all necessary details are included in the response, thereby simplifying client-side processing but increasing the payload size.

---

### Question 2:

Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

### Answer:

Yes, DELETE is idempotent, and making the request multiple times always ends up with the same end result. If a room has already been deleted, there will be no room in the system to delete. Making another request will have no additional effect on the system state.

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

Sub Resource Locator pattern will come handy while breaking down a big API into small pieces for manageability. The logic that would have been handled by the large single controller would now be handled by individual classes corresponding to each sub resource. It will help reduce code complexity and make it readable. Large systems could benefit from this pattern as it will increase maintainability and allow extension without interfering with the rest of the API.

---

## PART 5 — Error Handling & Logging

### Question 1:

Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

### Answer:

The HTTP 422 response is issued where the request sent by the client was syntactically correct, but contained either invalid or incorrect data. In this case, while the JSON format was correct, it referred to a non-existent resource, such as an invalid roomId. The error code 404 is issued where the requested endpoint simply does not exist. As a result, 422 offers a more accurate diagnosis of what went wrong. It helps clients understand that the issue is with the data, not the endpoint. This improves error clarity and API usability.

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
