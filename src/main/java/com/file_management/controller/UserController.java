package com.file_management.controller;

import com.file_management.helper.ApiResponse;
import com.file_management.models.AuthRequest;
import com.file_management.models.UserInfo;
import com.file_management.services.JwtService;
import com.file_management.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<Map<String, Object>>> addNewUser(
            @RequestParam("password") String password,
            @RequestParam("username") String username,
            @RequestParam("email") String email) {

        // Create UserInfo object from provided request parameters
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username); // Assuming `username` is the correct field in UserInfo class
        userInfo.setEmail(email);
        userInfo.setPassword(password);

        // Call the service to add the user and get the response
        ApiResponse<Map<String, Object>> response = service.addUser(userInfo);

        // Return the response entity
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        // Call the service to authenticate the user and get the response
        ApiResponse<Map<String, Object>> response = service.login(username, password);

        // Return the response entity
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}

