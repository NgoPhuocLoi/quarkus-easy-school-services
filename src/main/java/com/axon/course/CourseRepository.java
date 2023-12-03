package com.axon.course;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CourseRepository implements PanacheRepositoryBase<Course, Integer> {

}
