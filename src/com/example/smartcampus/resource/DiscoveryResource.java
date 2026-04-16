package com.example.smartcampus.resource;

import com.example.smartcampus.model.DiscoveryResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public DiscoveryResponse getApiInfo() {
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        return new DiscoveryResponse(
                "v1",
                "admin@smartcampus.com",
                resources
        );
    }
}