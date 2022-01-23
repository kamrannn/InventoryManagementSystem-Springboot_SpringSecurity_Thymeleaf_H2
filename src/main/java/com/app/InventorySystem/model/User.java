package com.app.InventorySystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fullName;
    @NotBlank(message = "username is mandatory")
    @Column(unique = true)
    String username;
    String password;
    String role;
    @NotBlank(message = "email is mandatory")
    @Column(unique = true)
    private String email;
}