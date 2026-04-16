package com.example.smartcampus.model;

import java.util.Map;

public class DiscoveryResponse {

    private String version;
    private String contact;
    private Map<String, String> resources;

    public DiscoveryResponse() {}

    public DiscoveryResponse(String version, String contact, Map<String, String> resources) {
        this.version = version;
        this.contact = contact;
        this.resources = resources;
    }

    public String getVersion() {
        return version;
    }

    public String getContact() {
        return contact;
    }

    public Map<String, String> getResources() {
        return resources;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }
}