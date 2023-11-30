package com.axon.classroom;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClassRepository implements PanacheRepositoryBase<Classroom, Integer> {

}
