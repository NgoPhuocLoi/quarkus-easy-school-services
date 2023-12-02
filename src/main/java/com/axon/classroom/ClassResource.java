package com.axon.classroom;

import java.util.List;

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

}
