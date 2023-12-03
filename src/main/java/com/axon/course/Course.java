package com.axon.course;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.axon.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Course's name must not be blank!")
    private String name;

    @NotNull(message = "Fee is missing!")
    @Min(value = 1, message = "Fee must be greater than 1")
    private double fee;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private Set<User> users;

    public Course(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getCourses().add(this);
    }

}
