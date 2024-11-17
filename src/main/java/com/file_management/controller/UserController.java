package com.file_management.controller;

import com.file_management.models.User;
import com.file_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

   @GetMapping("/users")
   public List<User> getlistUsers()
    {
        return  userRepository.findAll();
    }
}
