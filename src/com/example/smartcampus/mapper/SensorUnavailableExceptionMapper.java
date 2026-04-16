package com.example.smartcampus.mapper;

import com.example.smartcampus.exception.SensorUnavailableException;
import com.example.smartcampus.model.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException ex) {
        ApiError error = new ApiError(403, ex.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(error).build();
    }
}