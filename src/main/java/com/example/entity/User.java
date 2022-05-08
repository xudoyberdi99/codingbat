package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "email bush bulmasin")
    @Email
    private String email;
    @NotNull(message = "parol kamida 8 ta ko'pi bilan 15 ta")
    @Size(min = 8, max = 15)
    @Column(nullable = false)
    private String password;
}
