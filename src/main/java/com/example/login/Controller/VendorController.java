package com.example.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.DTO.VendorProductRequestDto;
import com.example.login.Entity.Order;
import com.example.login.Entity.ProductRequest;
import com.example.login.Service.VendorService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
    private VendorService vendorService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse<ProductRequest>> createRequest(
            @RequestParam String vendorName,
            @RequestBody VendorProductRequestDto dto) {
        ProductRequest request = vendorService.createRequest(vendorName, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Product request sent successfully", request));
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<Order>>> getOrders(@RequestParam String vendorName) {
        List<Order> orders = vendorService.getOrders(vendorName);
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders fetched successfully", orders));
    }

    @GetMapping("/requests")
    public ResponseEntity<ApiResponse<List<ProductRequest>>> getRequests(@RequestParam String vendorName) {
        List<ProductRequest> requests = vendorService.getRequests(vendorName);
        return ResponseEntity.ok(new ApiResponse<>(true, "Requests fetched successfully", requests));
    }
}