package com.file_management.models;

import jakarta.persistence.*;

@Table(name = "users_groups")
@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(nullable = false)
    private Boolean isAdmin;

    public UserGroup() {}

    public UserGroup(Integer id, User user, Group group, Boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
