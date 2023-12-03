package com.axon.classroom;

import java.util.List;

import com.axon.user.User;
import com.axon.user.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/classes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClassResource {

    @Inject
    ClassService classService;

    @Inject
    UserService userService;

    @GET
    public Response getAllClassrooms() {
        List<Classroom> classrooms = classService.getAllClassrooms();
        return Response.status(Response.Status.OK).entity(classrooms).build();
    }

    @POST
    public Response createClass(@Valid Classroom c) {
        classService.createClassroom(c);
        return Response.status(Response.Status.CREATED).entity("Class has been created").build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteClass(@PathParam("id") Integer id) {
        classService.deleteById(id);
        return Response.status(Response.Status.OK).entity("Classroom was deleted!").build();
    }

    @POST
    @Path("{id}/add-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToClass(@PathParam("id") Integer id, User user) {
        classService.addUserToClassByEmail(user.getEmail(), id);
        System.out.println("user: " + user);
        return Response.status(Response.Status.OK).entity("User has been added to class").build();
    }

    @GET
    @Path("{id}/users")
    public Response getUsersInClass(@PathParam("id") Integer id) {
        var users = userService.getUsersInClass(id);
        return Response.status(Response.Status.OK).entity(users).build();
    }
}
