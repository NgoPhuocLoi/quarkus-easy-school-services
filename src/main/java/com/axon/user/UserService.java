package com.axon.user;

import com.axon.classroom.ClassService;
import com.axon.utils.CustomException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import static jakarta.ws.rs.core.Response.Status.*;

import java.util.List;

@RequestScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    ClassService classService;

    @Transactional
    public Integer register(User user) {
        validateNotExistUserWithEmail(user.getEmail());
        userRepository.persist(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.listAll();

    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void validateNotExistUserWithEmail(String email) {
        var foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent())
            throw new CustomException(BAD_REQUEST, "Email has already existed!");
    }

    public void validateExistUserWithEmail(String email) {
        var foundUser = userRepository.findByEmail(email);
        if (foundUser.isEmpty())
            throw new CustomException(NOT_FOUND, "User not found!");
    }

    public List<UserDto> getUsersInClass(Integer classId) {
        classService.validateExistClass(classId);
        return userRepository.findUsersByClassId(classId).stream().map(this::convertEntityToDto).toList();
    }

    public UserDto convertEntityToDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
