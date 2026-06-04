package com.example.login.DTO;

import java.time.LocalDate;

import com.example.login.Entity.OrderStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {
	
	@NotBlank(message = "Vendor name is required")
    private String vendorName;

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;

    @NotNull(message = "Requested date is required")
    private LocalDate requestedDate;

}
