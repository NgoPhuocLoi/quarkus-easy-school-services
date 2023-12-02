package com.axon.user;

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

    @Transactional
    public Integer register(User user) {
        validateNotExistUserWithEmail(user.getEmail());
        userRepository.persist(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.listAll();

    }

    public void validateNotExistUserWithEmail(String email) {
        var foundUser = userRepository.findByEmail(email);
        System.out.println("foundUser: " + foundUser);
        if (foundUser.isPresent())
            throw new CustomException(BAD_REQUEST, "Email has already existed!");
    }

}
