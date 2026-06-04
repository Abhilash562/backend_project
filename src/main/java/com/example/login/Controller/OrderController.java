package com.example.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.DTO.OrderRequestDto;
import com.example.login.Service.OrderService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
    public ResponseEntity<?> addOrders(
            @Valid @RequestBody OrderRequestDto dto) {

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(true)
                        .message("Order Added Successfully")
                        .data(service.addOrder(dto))
                        .build(),
                HttpStatus.CREATED
        );
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<?> getAllOrders() {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Orders Fetched Successfully")
                        .data(service.getAllOrders())
                        .build()
        );
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Order Fetched Successfully")
                        .data(service.getOrderById(id))
                        .build()
        );
    }

}
