package com.axon.classroom;

import java.util.List;

import com.axon.CustomException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ClassService {

    @Inject
    ClassRepository classRepository;

    public List<Classroom> getAllClassrooms() {
        return classRepository.listAll();
    }

    @Transactional
    public Classroom createClassroom(Classroom c) {
        classRepository.persist(c);
        return c;
    }

    public Classroom findById(Integer id) {
        return classRepository.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        Classroom foundClass = this.findById(id);

        if (foundClass == null)
            throw new CustomException(Response.Status.BAD_REQUEST, "Classroom not found!");

        classRepository.deleteById(id);
    }
}
