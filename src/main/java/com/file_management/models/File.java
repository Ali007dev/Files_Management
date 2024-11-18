package com.file_management.models;

import jakarta.persistence.*;
import java.util.Set;

@Table(name = "files")
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String status;

//    @OneToMany(mappedBy = "file")
//    private Set<FileLog> fileLogs;

    public File() {
    }

    public File(Integer id, String file, Group group, User user, String status) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.file = file;
        this.status = status;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
