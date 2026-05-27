package com.example.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.DTO.ApiResponse;
import com.example.login.DTO.ProductRequestDto;
import com.example.login.Service.ProdutService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProdutService service;
	
	// Add Product
    @PostMapping
    public ResponseEntity<?> addProduct(
            @Valid @RequestBody ProductRequestDto dto) {

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(true)
                        .message("Product Added Successfully")
                        .data(service.addProduct(dto))
                        .build(),
                HttpStatus.CREATED
        );
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Products Fetched Successfully")
                        .data(service.getAllProducts())
                        .build()
        );
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Product Fetched Successfully")
                        .data(service.getProductById(id))
                        .build()
        );
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto dto) {

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Product Updated Successfully")
                        .data(service.updateProduct(id, dto))
                        .build()
        );
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Product Deleted Successfully")
                        .data(null)
                        .build()
        );
    }

}
