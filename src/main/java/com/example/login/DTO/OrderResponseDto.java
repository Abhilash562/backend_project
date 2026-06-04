package com.example.login.DTO;

import java.time.LocalDate;

import com.example.login.Entity.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {
	
	 private Long id;

	    private String vendorName;

	    private String supplierName;

	    private String productName;

	    private Integer quantity;

	    private OrderStatus orderStatus;

	    private LocalDate requestedDate;

}
