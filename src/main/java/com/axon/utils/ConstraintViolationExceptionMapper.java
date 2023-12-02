package com.axon.utils;

import java.util.List;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> details = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .toList();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage("The given input is invalid!", details))
                .build();
    }

}