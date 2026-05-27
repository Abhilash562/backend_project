package com.example.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.DTO.LoginRequest;
import com.example.login.DTO.LoginResponse;
import com.example.login.DTO.RegisterRequest;
import com.example.login.DTO.RegisterResponse;
import com.example.login.Entity.Users;
import com.example.login.Exceptions.UserNotFoundException;
import com.example.login.Service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class UsersController {
	
	@Autowired
    private UsersService userService;

	@PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        Users user = userService.register(request);

        RegisterResponse response = new RegisterResponse();
        response.setUniqueUserId(user.getUniqueUserId());
        response.setName(user.getName());
        response.setMobileNumber(user.getMobileNumber());
        response.setRole(user.getRole().name());
        response.setLocation(user.getLocation());
        response.setBusinessName(user.getBusinessName());
        response.setBusinessType(user.getBusinessType());

        return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", response));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        Users user = userService.getUserByMobile(request.getMobileNumber());
        String token = userService.login(request.getMobileNumber());

        LoginResponse response = new LoginResponse(token, user.getRole().name());
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", response));
    }
    
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<RegisterResponse>> getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal == null || !(principal instanceof Users)) {
            throw new UserNotFoundException("Logged-in user not found");
        }
        
        Users user = (Users) principal;

        RegisterResponse response = new RegisterResponse();
        response.setUniqueUserId(user.getUniqueUserId());
        response.setName(user.getName());
        response.setMobileNumber(user.getMobileNumber());
        response.setRole(user.getRole().name());
        response.setLocation(user.getLocation());
        response.setBusinessName(user.getBusinessName());
        response.setBusinessType(user.getBusinessType());

        return ResponseEntity.ok(new ApiResponse<>(true, "User details fetched successfully", response));
    }
}
