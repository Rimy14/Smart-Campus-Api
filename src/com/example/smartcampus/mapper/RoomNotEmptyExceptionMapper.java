package com.example.smartcampus.mapper;

import com.example.smartcampus.exception.RoomNotEmptyException;
import com.example.smartcampus.model.ApiError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException ex) {
        ApiError error = new ApiError(409, ex.getMessage());
        return Response.status(409).entity(error).build();
    }
}