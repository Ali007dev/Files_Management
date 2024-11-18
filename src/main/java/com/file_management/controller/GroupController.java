package com.file_management.controller;

import com.file_management.models.Group;
import com.file_management.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @GetMapping("/groups")
    public List<Group> groups(){
        return groupRepository.findAll();
    }


}
