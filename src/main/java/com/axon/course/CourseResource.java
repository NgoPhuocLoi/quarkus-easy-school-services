package com.axon.course;

import java.net.URI;

import com.axon.user.User;

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
import static jakarta.ws.rs.core.Response.Status.*;
import jakarta.ws.rs.core.UriInfo;

@Path("/courses")
public class CourseResource {

    @Inject
    CourseService courseService;

    @Inject
    CourseRepository courseRepository;

    @Inject
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourse(@Valid Course course) {
        Integer courseId = courseService.createCourse(course);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(courseId)).build();

        return Response.status(CREATED).entity(uri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses() {
        var courses = courseService.getAllCourses();
        return Response.status(OK).entity(courses).build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseById(@PathParam("id") Integer id) {
        var course = courseService.getCourseById(id);
        return Response.status(OK).entity(course).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteCourseById(@PathParam("id") Integer id) {
        courseService.deleteCourseById(id);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
        return Response.status(OK).entity(uri).build();
    }

    @Path("{id}/add-user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToCourse(@PathParam("id") Integer id, User user) {
        courseService.addUserToCourse(user.getEmail(), id);
        return Response.status(OK).entity("User was added to the course!").build();
    }

    @Path("{id}/users")
    @GET
    public Response getUsersInCourse(@PathParam("id") Integer id) {
        Course course = courseRepository.findById(id);
        // var result = courseRepository.find("SELECT ", null)
        return Response.status(OK).entity(course.getUsers()).build();
    }
}
