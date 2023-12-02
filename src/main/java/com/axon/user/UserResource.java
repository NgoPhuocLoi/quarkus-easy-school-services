package com.axon.user;

import java.net.URI;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import static jakarta.ws.rs.core.Response.Status.*;

import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@Valid User user) {
        Integer userId = userService.register(user);
        URI location = this.uriInfo.getAbsolutePathBuilder().path(String.valueOf(userId)).build();
        return Response.status(CREATED).entity(location).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.status(OK).entity(users).build();
    }
}
