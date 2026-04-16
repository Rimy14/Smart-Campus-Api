package com.example.smartcampus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;

public class Main {

    private static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.example.smartcampus");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        HttpServer server = startServer();

        System.out.println("Server started at: " + BASE_URI);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.err.println("Server stopped.");
        }
    }
}