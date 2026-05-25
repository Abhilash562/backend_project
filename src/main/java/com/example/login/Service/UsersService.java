package com.example.login.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.Config.JwtUtil;
import com.example.login.DTO.RegisterRequest;
import com.example.login.Entity.Users;
import com.example.login.Repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
    private UsersRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Users register(RegisterRequest request) {
        if (userRepository.findByMobileNumber(request.getMobileNumber()).isPresent()) {
            throw new RuntimeException("Mobile number already exists");
        }

        Users user = new Users();
        user.setName(request.getName());
        user.setMobileNumber(request.getMobileNumber());
        user.setRole(Users.Role.valueOf(request.getRole().toUpperCase()));
        user.setLocation(request.getLocation());
        user.setBusinessName(request.getBusinessName());
        user.setBusinessType(request.getBusinessType());

        String prefix = switch (user.getRole()) {
            case SUPPLIER -> "SUP";
            case VENDOR -> "VEN";
            case ADMIN -> "ADM";
            default -> "USR";
        };

        long count = userRepository.count() + 1;
        user.setUniqueUserId(prefix + String.format("%03d", count));

        return userRepository.save(user);
    }

    public String login(String mobileNumber) {
        Optional<Users> userOpt = userRepository.findByMobileNumber(mobileNumber);
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        return jwtUtil.generateToken(userOpt.get());
    }
    
    public Users getUserByMobile(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
