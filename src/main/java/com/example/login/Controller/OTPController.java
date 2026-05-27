package com.example.login.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.SendOtpRequest;
import com.example.login.DTO.VerifyOtpRequest;
import com.example.login.Entity.Users;
import com.example.login.Repository.UsersRepository;
import com.example.login.Service.OtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class OTPController {
	
	@Autowired
    private OtpService otpService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@PostMapping("/sendOTP")
	public ResponseEntity<Map<String, String>> sendOtp(
	        @Valid @RequestBody SendOtpRequest request) {

	    Map<String, String> response = new HashMap<>();

	    Optional<Users> userOpt = usersRepository.findByMobileNumber(request.getMobileNumber());

	    if (userOpt.isEmpty()) {
	        response.put("message", "Mobile number not registered");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    Users user = userOpt.get();

	    String otp = otpService.generateOtp(request.getMobileNumber());

	    response.put("message", "OTP sent successfully");
	    response.put("role", user.getRole().name()); // 👈 add role here

	    return ResponseEntity.ok(response);
	}

	@PostMapping("/verifyOTP")
	public ResponseEntity<Map<String, String>> verifyOtp(
	        @Valid @RequestBody VerifyOtpRequest request) {

	    boolean valid = otpService.verifyOtp(
	            request.getMobileNumber(),
	            request.getOtp()
	    );

	    Map<String, String> response = new HashMap<>();

	    // INVALID OTP
	    if (!valid) {
	        response.put("status", "INVALID OTP");

	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED) // or 400 Bad Request
	                .body(response);
	    }

	    // SUCCESS
	    response.put("status", "VERIFIED");

	    return ResponseEntity
	            .status(HttpStatus.OK)
	            .body(response);
	}

}
