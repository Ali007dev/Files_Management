package com.file_management.models;

import jakarta.persistence.*;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<File> files;

    @OneToMany(mappedBy = "user")
    private Set<UserGroup> userGroups;

    public User() {
    }

    public User(Integer id, String name, String email, String number, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
