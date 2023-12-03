package com.axon.course;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

import com.axon.user.User;
import com.axon.user.UserRepository;
import com.axon.user.UserService;
import com.axon.utils.CustomException;

@ApplicationScoped
public class CourseService {

    @Inject
    CourseRepository courseRepository;

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

    @Transactional
    public Integer createCourse(Course course) {
        courseRepository.persist(course);
        return course.getId();
    }

    public List<Course> getAllCourses() {
        return courseRepository.listAll();
    }

    public Course getCourseById(Integer id) {
        this.validateExistCourse(id);
        return courseRepository.findById(id);
    }

    @Transactional
    public void deleteCourseById(Integer id) {
        this.validateExistCourse(id);
        courseRepository.deleteById(id);
    }

    @Transactional
    public void addUserToCourse(String email, Integer courseId) {
        User user = userService.getByEmail(email);
        Course course = getCourseById(courseId);
        System.out.println(user.getCourses());
        System.out.println(course.getUsers());
        // if()
        // course.addUser(user);
        // course.getUsers().add(user);
        user.getCourses().add(course);
        // userRepository.persistAndFlush(user);
        // courseRepository.persist(course);
        // System.out.println("User: " + user);
        // System.out.println("Course: " + course);
    }

    public void validateExistCourse(Integer id) {
        var foundCourse = courseRepository.findByIdOptional(id);
        if (foundCourse.isEmpty())
            throw new CustomException(Response.Status.NOT_FOUND, "Course not found!");
    }

}
