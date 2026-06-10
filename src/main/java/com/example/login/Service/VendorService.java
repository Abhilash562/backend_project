package com.example.login.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.VendorProductRequestDto;
import com.example.login.Entity.Order;
import com.example.login.Entity.Product;
import com.example.login.Entity.ProductRequest;
import com.example.login.Exceptions.ResourceNotFoundException;
import com.example.login.Repository.OrderRepository;
import com.example.login.Repository.ProductRepository;
import com.example.login.Repository.ProductRequestRepository;

@Service
public class VendorService {
	
	@Autowired
	private ProductRequestRepository requestRepo;
	
	@Autowired
    private OrderRepository orderRepo;
	
	@Autowired
    private ProductRepository productRepo;

    // Create Product Request
    public ProductRequest createRequest(String vendorName, VendorProductRequestDto dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        ProductRequest request = ProductRequest.builder()
                .vendorName(vendorName)
                .productId(product.getId())
                .productName(product.getProductName())
                .quantity(dto.getQuantity())
                .message(dto.getMessage())
                .status("Pending")
                .requestedDate(LocalDate.now())
                .build();

        return requestRepo.save(request);
    }

    // Get Vendor Orders
    public List<Order> getOrders(String vendorName) {
        List<Order> orders = orderRepo.findByVendorName(vendorName);
        if (orders.isEmpty()) throw new ResourceNotFoundException("No orders found");
        return orders;
    }

    // Get Vendor Requests
    public List<ProductRequest> getRequests(String vendorName) {
        List<ProductRequest> requests = requestRepo.findByVendorName(vendorName);
        if (requests.isEmpty()) throw new ResourceNotFoundException("No requests found");
        return requests;
    }

}
