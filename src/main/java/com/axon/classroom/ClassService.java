package com.axon.classroom;

import java.util.List;

import com.axon.user.User;
import com.axon.user.UserRepository;
import com.axon.user.UserService;
import com.axon.utils.CustomException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.Status.*;

@RequestScoped
public class ClassService {

    @Inject
    ClassRepository classRepository;

    @Inject
    UserService userService;

    @Inject
    UserRepository userRepository;

    public List<Classroom> getAllClassrooms() {
        return classRepository.listAll();
    }

    @Transactional
    public Classroom createClassroom(Classroom c) {
        classRepository.persist(c);
        return c;
    }

    public Classroom getById(Integer id) {
        return classRepository.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        Classroom foundClass = this.getById(id);

        if (foundClass == null)
            throw new CustomException(Response.Status.BAD_REQUEST, "Classroom not found!");

        classRepository.deleteById(id);
    }

    @Transactional
    public void addUserToClassByEmail(String email, Integer classId) {
        userService.validateExistUserWithEmail(email);
        validateExistClass(classId);

        User user = userService.getByEmail(email);
        Classroom classroom = getById(classId);

        if (user.getClassroom().getId() == classId)
            throw new CustomException(BAD_REQUEST, "This user has been in this class!");

        user.setClassroom(classroom);
        // classRepository.persistAndFlush(classroom);
        userRepository.persist(user);
    }

    public void validateExistClass(Integer classId) {
        var foundClass = classRepository.findByIdOptional(classId);
        if (foundClass.isEmpty())
            throw new CustomException(NOT_FOUND, "Class not found!");
    }

    public void validateUserAlreadyInClass(String email, Integer classId) {

    }
}
