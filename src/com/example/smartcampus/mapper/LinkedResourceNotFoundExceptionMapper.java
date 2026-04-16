package com.example.smartcampus.mapper;

import com.example.smartcampus.exception.LinkedResourceNotFoundException;
import com.example.smartcampus.model.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {
        ApiError error = new ApiError(422, ex.getMessage());
        return Response.status(422).entity(error).build();
    }
}