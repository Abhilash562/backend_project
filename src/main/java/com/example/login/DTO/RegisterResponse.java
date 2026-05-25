package com.example.login.DTO;

import lombok.Data;

@Data
public class RegisterResponse {
    private String uniqueUserId;
    private String name;
    private String mobileNumber;
    private String role;
    private String location;
    private String businessName;
    private String businessType;
}