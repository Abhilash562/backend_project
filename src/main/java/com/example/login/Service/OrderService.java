package com.example.login.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.DTO.OrderRequestDto;
import com.example.login.DTO.OrderResponseDto;
import com.example.login.Entity.Order;
import com.example.login.Exceptions.ResourceNotFoundException;
import com.example.login.Repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired 
	private OrderRepository repository;
	
	public OrderResponseDto addOrder(OrderRequestDto dto) {

        Order order = Order.builder()
                .vendorName(dto.getVendorName())
                .supplierName(dto.getSupplierName())
                .productName(dto.getProductName())
                .quantity(dto.getQuantity())
                .orderStatus(dto.getOrderStatus())
                .requestedDate(dto.getRequestedDate())
                .build();

        Order savedOrder = repository.save(order);

        return mapToResponse(savedOrder);
    }

    // Get All Products
    public List<OrderResponseDto> getAllOrders() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Product By ID
    public OrderResponseDto getOrderById(Long id) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with ID: " + id));

        return mapToResponse(order);
    }
    
    private OrderResponseDto mapToResponse(Order order) {

        return OrderResponseDto.builder()
                .id(order.getId())
                .vendorName(order.getVendorName())
                .supplierName(order.getSupplierName())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .requestedDate(order.getRequestedDate())
                .build();
    }

}
