package com.file_management.services;

import com.file_management.helper.ApiResponse;
import com.file_management.models.UserInfo;
import com.file_management.repository.UserInfoRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username).map(Optional::ofNullable).orElse(Optional.empty()); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(user -> User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER") // Assuming a default role, modify as needed
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }


    @Autowired
    private JwtService jwtService;

    public ApiResponse<Map<String, Object>> addUser(UserInfo userInfo) {
        // Encode the password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        UserInfo savedUser = repository.save(userInfo);

        Map<String, Object> claims = new HashMap<>();

        // Generate JWT token for the user
        String token = jwtService.createToken(claims, savedUser.getName());

        // Create a response data map containing the user info and token
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", savedUser.getId());
        responseData.put("username", savedUser.getName());
        responseData.put("email", savedUser.getEmail());
        responseData.put("token", token);

        // Return the response using the ApiResponse wrapper
        return new ApiResponse<>("User Added Successfully", 201, responseData);
    }


    public ApiResponse<Map<String, Object>> login(String email, String password) {
        // Fetch the user by email
        Optional<UserInfo> userInfoOptional = repository.findByEmail(email);
        if (!userInfoOptional.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }

        UserInfo userInfo = userInfoOptional.get();

        // Check if the provided password matches the encoded password stored in the database
        if (!encoder.matches(password, userInfo.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Create claims or additional data for JWT if necessary
        Map<String, Object> claims = new HashMap<>();

        // Generate JWT token for the user
        String token = jwtService.createToken(claims, userInfo.getName());

        // Create a response data map containing the user info and token
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", userInfo.getId());
        responseData.put("username", userInfo.getName());
        responseData.put("email", userInfo.getEmail());
        responseData.put("token", token);

        // Return the response using the ApiResponse wrapper
        return new ApiResponse<>("Login Successful", 200, responseData);
    }


}
