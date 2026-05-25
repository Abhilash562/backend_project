package com.example.login.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank(message = "Mobile number is required")
    private String mobileNumber;
}
