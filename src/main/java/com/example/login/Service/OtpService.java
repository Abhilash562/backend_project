package com.example.login.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	private final Map<String, String> otpStorage = new HashMap<>();

	public String generateOtp(String mobileNumber) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStorage.put(mobileNumber, otp);

        System.out.println("OTP sent to " + mobileNumber + " : " + otp);

        return otp;
    }

    public boolean verifyOtp(String mobileNumber, String otp) {
        if (!otpStorage.containsKey(mobileNumber)) {
            return false;
        }

        boolean isValid = otpStorage.get(mobileNumber).equals(otp);

        if (isValid) {
            otpStorage.remove(mobileNumber); 
        }

        return isValid;
    }

}
