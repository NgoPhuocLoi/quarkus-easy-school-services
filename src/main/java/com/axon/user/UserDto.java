package com.axon.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is missing")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is missing")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is missing!")
    private String email;
}
