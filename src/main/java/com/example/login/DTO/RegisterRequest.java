package com.example.login.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Role is required")
    private String role; // ADMIN, SUPPLIER, VENDOR

    private String location;
    private String businessName;
    private String businessType;
}
