package com.app.InventorySystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    String id = UUID.randomUUID().toString();
    @NotBlank(message = "full name is mandatory")
    String fullName;
    @NotBlank(message = "username is mandatory")
    @Column(unique = true)
    String username;
    @NotBlank
    String password;
    String role;
    @NotBlank(message = "email is mandatory")
    @Column(unique = true)
    private String email;
}