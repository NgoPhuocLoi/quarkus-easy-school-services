package com.axon;

import com.axon.utils.ErrorMessage;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class MyException extends WebApplicationException {
    public MyException(Response.Status status, String message) {
        super(Response.status(status).entity(new ErrorMessage(message)).build());
    }
}
