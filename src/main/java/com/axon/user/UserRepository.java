package com.axon.user;

import java.util.Optional;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserRepository implements PanacheRepositoryBase<User, Integer> {
    public Optional<User> findByEmail(String email) {
        return find("email = ?1", email).firstResultOptional();
    }

    public List<User> findUsersByClassId(Integer classId) {
        return find("classroom.id = ?1", classId).list();
    }
}
