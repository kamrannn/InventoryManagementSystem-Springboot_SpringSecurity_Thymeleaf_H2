package com.app.InventorySystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

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

    public User() {
    }

    public User(@NotBlank(message = "full name is mandatory") String fullName, @NotBlank(message = "username is mandatory") String username, @NotBlank String password, String role, @NotBlank(message = "email is mandatory") String email) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}