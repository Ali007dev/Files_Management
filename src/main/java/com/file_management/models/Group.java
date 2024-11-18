package com.file_management.models;

import jakarta.persistence.*;
import java.util.Set;

@Table(name = "groups")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<File> files;

    @OneToMany(mappedBy = "group")
    private Set<UserGroup> userGroups;

    public Group() {}

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
