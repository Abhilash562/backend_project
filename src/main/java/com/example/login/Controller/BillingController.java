package com.example.login.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.Service.BillingService;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

	@Autowired
    private BillingService service;

    @GetMapping("/summary")
    public ApiResponse<Map<String, Object>> getSummary() {
        return new ApiResponse<>(true, "Billing summary", service.getBillingSummary());
    }
}
