package com.example.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.Service.ReportService;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {
	
	@Autowired
    private ReportService reportService;

    @GetMapping("/summary")
    public ApiResponse<Object> getDashboardSummary() {
        return new ApiResponse<>(true, "Dashboard Summary", reportService.getDashboardSummary());
    }
    
    @GetMapping("/top-products")
    public ResponseEntity<?> getTopProducts() {

        return ResponseEntity.ok(
            ApiResponse.builder()
                .success(true)
                .message("Top Products Fetched Successfully")
                .data(reportService.getTopProducts())
                .build()
        );
    }
    
    @GetMapping("/sales-summary")
    public ResponseEntity<?> getSalesSummary() {

        return ResponseEntity.ok(
            ApiResponse.builder()
                .success(true)
                .message("Sales Summary Fetched Successfully")
                .data(reportService.getSalesSummary())
                .build()
        );
    }

}
