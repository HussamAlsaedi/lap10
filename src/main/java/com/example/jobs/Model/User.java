package com.example.jobs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor @Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "Name is required.")
    @Size(min = 5, message = "Name length must be more than 4 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabetic characters (no numbers or special characters).")
    private String name;

    @Column(columnDefinition = "varchar(60) unique not null")
    @Email(message = "Email not correct.")
    private String email;

    @Column(columnDefinition = "varchar(21) not null")
    @Pattern(regexp = "^[0-9]+$", message = "password Must be a number.")
    @Min(value = 21, message = "password Length must be more than 21 characters.")
    private String password;

    @NotEmpty(message = "Role is required.")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "Must be either JOB_SEEKER or EMPLOYER only.")
    private String role;
}
